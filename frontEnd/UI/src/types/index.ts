export interface User {
  email: string;
  name?: string;
}

export interface AuthState {
  user: User | null;
  isAuthenticated: boolean;
  isLoading: boolean;
}

export interface Product {
  id: string;
  productName: string;
  sellPrice: number;
  purchasePrice: number;
  description?: string;
  stock: number;
  category: string;
  supplier: string;
  brand: string;
  createAt?: string;
}

export interface ProductSearchResponse {
  content: Product[];
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
}

export interface Category {
  categoryCode: string;
  categoryName: string;
}

export interface Brand {
  id: string;
  brandName: string;
  brandCode: string;
  supplierName: string;
}

export interface Supplier {
  id: string;
  supplierName: string;
  phoneNumber: string;
  contactPerson: string;
  desc?: string;
}

export interface WebResponse<T> {
  status: number;
  code: string;
  message: string;
  data: T;
}

export interface ApiError {
  error: boolean;
  message: string;
}