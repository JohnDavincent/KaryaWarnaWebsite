import axios from "axios";

const API_BASE_URL = import.meta.env.VITE_API_URL || "http://localhost:8100";
const PRODUCT_API_BASE_URL = import.meta.env.VITE_PRODUCT_API_URL || "http://localhost:8101";

// Axios instance for user-services (auth, profile)
export const api = axios.create({
  baseURL: API_BASE_URL,
  withCredentials: true,
  headers: {
    "Content-Type": "application/json",
  },
});

// Axios instance for product-services (products, categories, brands, suppliers)
export const productServiceApi = axios.create({
  baseURL: PRODUCT_API_BASE_URL,
  withCredentials: true,
  headers: {
    "Content-Type": "application/json",
  },
});

let isRedirecting = false;

// Response interceptor for error handling (apply to both instances)
const errorInterceptor = (error: any) => {
  if (error.response?.status === 401 && !isRedirecting) {
    isRedirecting = true;
    localStorage.removeItem("user");
    window.location.href = "/login";
  }
  return Promise.reject(error);
};

api.interceptors.response.use((response) => response, errorInterceptor);
productServiceApi.interceptors.response.use((response) => response, errorInterceptor);

// Auth API (user-services @ port 8100)
export const authApi = {
  login: (email: string, password: string) =>
    api.post("/karyawarna/login", { email, password }),

  register: (data: { username: string; password: string; email: string }) =>
    api.post("/karyawarna/register", data),

  getProfile: () => api.get("/karyawarna/profile"),
};

// Products API (product-services @ port 8101)
export const productApi = {
  search: (params?: {
    page?: number;
    size?: number;
    productName?: string;
    category?: string;
    brand?: string;
  }) => productServiceApi.get("/karyawarna/admin/search", { params }),

  create: (data: {
    productName: string;
    sellPrice: number;
    purchasePrice: number;
    description?: string;
    stock: number;
    category: string;
    supplier: string;
    brand: string;
  }) => productServiceApi.post("/karyawarna/admin/create/product", data),

  update: (
    id: string,
    data: Partial<{
      productName: string;
      sellPrice: number;
      purchasePrice: number;
      description: string;
      stock: number;
      category: string;
      supplier: string;
      brand: string;
    }>,
  ) => productServiceApi.patch(`/karyawarna/admin/update/product/${id}`, data),

  delete: (id: string) => productServiceApi.delete(`/karyawarna/admin/delete/product/${id}`),
};

// Categories API (product-services @ port 8101)
export const categoryApi = {
  create: (data: { categoryName: string; categoryCode: string }) =>
    productServiceApi.post("/karyawarna/admin/create/category", data),

  update: (
    categoryCode: string,
    data: { categoryName: string; categoryCode: string },
  ) => productServiceApi.patch(`/karyawarna/admin/update/category/${categoryCode}`, data),
};

// Brands API (product-services @ port 8101)
export const brandApi = {
  create: (data: {
    brandName: string;
    brandCode: string;
    supplierName: string;
    categoryIds?: string[];
  }) => productServiceApi.post("/karyawarna/admin/create/brand", data),
};

// Suppliers API (product-services @ port 8101)
export const supplierApi = {
  create: (data: {
    supplierName: string;
    phoneNumber: string;
    contactPerson: string;
    desc?: string;
  }) => productServiceApi.post("/karyawarna/admin/create/supplier", data),

  update: (
    id: string,
    data: {
      supplierName: string;
      phoneNumber: string;
      contactPerson: string;
      desc?: string;
    },
  ) => productServiceApi.patch(`/karyawarna/admin/update/supplier/${id}`, data),

  delete: (id: string) => productServiceApi.delete(`/karyawarna/admin/delete/supplier/${id}`),
};

export default api;
