import axios from "axios";

const API_BASE_URL = import.meta.env.VITE_API_URL || "http://localhost:8100";

export const api = axios.create({
  baseURL: API_BASE_URL,
  withCredentials: true,
  headers: {
    "Content-Type": "application/json",
  },
});

// Response interceptor for error handling
api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      // Token expired or invalid
      localStorage.removeItem("user");
      window.location.href = "/login";
    }
    return Promise.reject(error);
  },
);

// Auth API
export const authApi = {
  login: (email: string, password: string) =>
    api.post("/karyawarna/login", { email, password }),

  register: (data: { username: string; password: string; email: string }) =>
    api.post("/karyawarna/register", data),

  getProfile: () => api.get("/karyawarna/profile"),
};

// Products API
export const productApi = {
  search: (params?: {
    page?: number;
    size?: number;
    productName?: string;
    category?: string;
    brand?: string;
  }) => api.get("/karyawarna/admin/search", { params }),

  create: (data: {
    productName: string;
    sellPrice: number;
    purchasePrice: number;
    description?: string;
    stock: number;
    category: string;
    supplier: string;
    brand: string;
  }) => api.post("/karyawarna/admin/create/product", data),

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
  ) => api.patch(`/karyawarna/admin/update/product/${id}`, data),

  delete: (id: string) => api.delete(`/karyawarna/admin/delete/product/${id}`),
};

// Categories API
export const categoryApi = {
  create: (data: { categoryName: string; categoryCode: string }) =>
    api.post("/karyawarna/admin/create/category", data),

  update: (
    categoryCode: string,
    data: { categoryName: string; categoryCode: string },
  ) => api.patch(`/karyawarna/admin/update/category/${categoryCode}`, data),
};

// Brands API
export const brandApi = {
  create: (data: {
    brandName: string;
    brandCode: string;
    supplierName: string;
    categoryIds?: string[];
  }) => api.post("/karyawarna/admin/create/brand", data),
};

// Suppliers API
export const supplierApi = {
  create: (data: {
    supplierName: string;
    phoneNumber: string;
    contactPerson: string;
    desc?: string;
  }) => api.post("/karyawarna/admin/create/supplier", data),

  update: (
    id: string,
    data: {
      supplierName: string;
      phoneNumber: string;
      contactPerson: string;
      desc?: string;
    },
  ) => api.patch(`/karyawarna/admin/update/supplier/${id}`, data),

  delete: (id: string) => api.delete(`/karyawarna/admin/delete/supplier/${id}`),
};

export default api;
