import { useEffect, useState } from "react";
import Navbar from "../components/Navbar";
import { addProduct, getBrands, getCategories, getSupplier } from "../fetch/inventory";
import type { BrandItem, CategoryItem, SupplierItem } from "../types/inventory";

const currencyFormatter = new Intl.NumberFormat("id-ID");

const initialFormData = {
  productName: "",
  purchasePrice: 0,
  sellPrice: 0,
  brand: "",
  category: "",
  supplier: "",
  stock: 0,
  description: "",
};

function Product() {
  const [formData, setFormData] = useState(initialFormData);
  const [suppliers, setSuppliers] = useState<SupplierItem[]>([]);
  const [brands, setBrands] = useState<BrandItem[]>([]);
  const [categories, setCategories] = useState<CategoryItem[]>([]);
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [submitFeedback, setSubmitFeedback] = useState<{
    tone: "success" | "error";
    message: string;
  } | null>(null);

  const hasPricingValues = formData.purchasePrice > 0 && formData.sellPrice > 0;
  const profitAmount = formData.sellPrice - formData.purchasePrice;
  const profitPercentage = hasPricingValues
    ? (profitAmount / formData.purchasePrice) * 100
    : 0;
  const isProfitable = profitAmount > 0;
  const profitToneClass = isProfitable
    ? "border-emerald-500/20 bg-emerald-500/10 text-emerald-300"
    : "border-red-500/20 bg-red-500/10 text-red-300";
  const profitLabel = isProfitable ? "Profit" : "Loss";

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement | HTMLSelectElement>
  ) => {
    const { name, value } = e.target;

    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handlePriceChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    const numericValue = value.replace(/\D/g, "");

    setFormData((prev) => ({
      ...prev,
      [name]: numericValue ? parseInt(numericValue, 10) : 0,
    }));
  };

  const handleStockChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const numericValue = e.target.value.replace(/\D/g, "");

    setFormData((prev) => ({
      ...prev,
      stock: numericValue ? parseInt(numericValue, 10) : 0,
    }));
  };


  const [isSuccessCreate, setSuccessCreate] = useState(false);

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    if (!formData.productName.trim()) {
      setSubmitFeedback({
        tone: "error",
        message: "Nama produk wajib diisi.",
      });
      return;
    }

    if (formData.purchasePrice <= 0 || formData.sellPrice <= 0) {
      setSubmitFeedback({
        tone: "error",
        message: "Harga dasar dan harga jual wajib diisi.",
      });
      return;
    }

    if (!formData.brand || !formData.category || !formData.supplier) {
      setSubmitFeedback({
        tone: "error",
        message: "Brand, kategori, dan supplier wajib dipilih.",
      });
      return;
    }

    try {
      setIsSubmitting(true);
      setSubmitFeedback(null);

      const result = await addProduct(formData);

      if (result?.success === false) {
        throw new Error(result.message ?? "Gagal menambahkan produk.");
      }

      setFormData(initialFormData);
      setSubmitFeedback({
        tone: "success",
        message: "Produk berhasil ditambahkan.",
      });
    } catch (error) {
      setSubmitFeedback({
        tone: "error",
        message: error instanceof Error ? error.message : "Gagal menambahkan produk.",
      });
    } finally {
      setIsSubmitting(false);
    }
  };

  useEffect(() => {
    const loadData = async () => {
      const categoryResult = await getCategories();
      const supplierResult = await getSupplier();
      const brandResult = await getBrands();

      setCategories(categoryResult.data);
      setSuppliers(supplierResult.data);
      setBrands(brandResult.data);
    };

    loadData();
  }, []);


  

  return (
    <div className="bg-[#131313] min-h-screen">
      <Navbar />
      <main className="ml-20 peer-hover:ml-64 transition-all duration-300 pt-24 pb-16 px-12 max-w-7xl">
        <form onSubmit={handleSubmit}>
          <div className="flex justify-between items-end mb-10">
            <div>
              <span className="text-xs font-bold tracking-[0.2em] text-primary uppercase font-label">
                Pengaturan inventoris
              </span>
              <h2 className="text-4xl font-extrabold font-headline tracking-tight text-on-surface mt-1">
                Tambah Produk
              </h2>
            </div>
            <div className="flex gap-4">
              <button
                className="px-6 py-2.5 text-sm font-semibold text-on-surface-variant hover:bg-surface-container-high transition-colors rounded-md"
                type="button"
              >
                Discard
              </button>
              <button
                className="px-8 py-2.5 text-sm font-bold text-on-primary-container kinetic-gradient-primary rounded-md shadow-lg shadow-primary/10 disabled:cursor-not-allowed disabled:opacity-70"
                disabled={isSubmitting}
                type="submit"
              >
                {isSubmitting ? "Menyimpan..." : "Tambahkan Produk"}
              </button>
            </div>
          </div>

          <div className="grid grid-cols-12 gap-8">
            <div className="col-span-8 space-y-8">
              <section className="bg-surface-container-low rounded-lg shadow-sm border border-outline-variant/5 p-10">
                <div className="flex items-center gap-3 mb-10">
                  <span className="material-symbols-outlined text-primary bg-primary/10 p-2 rounded-lg">info</span>
                  <h3 className="text-xs font-bold font-headline uppercase tracking-[0.2em] text-on-surface">
                    Informasi Produk
                  </h3>
                </div>

                <div className="space-y-8">
                  <div className="space-y-2">
                    <label className="text-xs font-semibold uppercase tracking-wider text-[#E5E2E1]" htmlFor="productName">
                      Nama Produk
                    </label>
                    <input
                      className="w-full bg-[#1C1B1B] border border-outline-variant/20 hover:border-outline-variant/50 focus:border-primary focus:ring-1 focus:ring-primary/20 rounded-md px-4 py-3 text-sm text-on-surface transition-all placeholder:text-[#8B90A0]/50 outline-none"
                      name="productName"
                      onChange={handleChange}
                      placeholder="contoh : Cat Avitex base A 5 kg"
                      type="text"
                      value={formData.productName}
                    />
                  </div>

                  <div className="grid grid-cols-2 gap-8">
                    <div className="space-y-2">
                      <label className="text-xs font-semibold uppercase tracking-wider text-[#E5E2E1]" htmlFor="purchasePrice">
                        Harga dasar
                      </label>
                      <div className="relative">
                        <span className="absolute left-4 top-1/2 -translate-y-1/2 text-[#8B90A0] text-sm">Rp</span>
                        <input
                          className="w-full bg-[#1C1B1B] border border-outline-variant/20 hover:border-outline-variant/50 focus:border-primary focus:ring-1 focus:ring-primary/20 rounded-md pl-12 pr-4 py-3 text-sm text-on-surface transition-all placeholder:text-[#8B90A0]/50 outline-none"
                          name="purchasePrice"
                          onChange={handlePriceChange}
                          placeholder="0.00"
                          type="text"
                          value={formData.purchasePrice === 0 ? "" : currencyFormatter.format(formData.purchasePrice)}
                        />
                      </div>
                    </div>

                    <div className="space-y-2">
                      <label className="text-xs font-semibold uppercase tracking-wider text-[#E5E2E1]" htmlFor="sellPrice">
                        Harga Jual
                      </label>
                      <div className="relative">
                        <span className="absolute left-4 top-1/2 -translate-y-1/2 text-[#8B90A0] text-sm">Rp</span>
                        <input
                          className="w-full bg-[#1C1B1B] border border-outline-variant/20 hover:border-outline-variant/50 focus:border-primary focus:ring-1 focus:ring-primary/20 rounded-md pl-12 pr-4 py-3 text-sm text-on-surface transition-all placeholder:text-[#8B90A0]/50 outline-none"
                          name="sellPrice"
                          onChange={handlePriceChange}
                          placeholder="0.00"
                          type="text"
                          value={formData.sellPrice === 0 ? "" : currencyFormatter.format(formData.sellPrice)}
                        />
                      </div>
                    </div>
                  </div>

                  {hasPricingValues && (
                    <div
                      aria-live="polite"
                      className={`rounded-md border px-4 py-4 transition-colors ${profitToneClass}`}
                    >
                      <div className="flex items-start justify-between gap-4">
                        <div className="space-y-1">
                          <p className="text-xs font-semibold uppercase tracking-[0.18em]">
                            {profitLabel} per unit
                          </p>
                          <p className="text-2xl font-bold font-headline">
                            Rp {currencyFormatter.format(Math.abs(profitAmount))}
                          </p>
                        </div>
                        <div className="text-right">
                          <p className="text-xs font-semibold uppercase tracking-[0.18em]">
                            Percentage
                          </p>
                          <p className="text-lg font-bold">
                            {profitAmount > 0 ? "+" : ""}
                            {profitPercentage.toFixed(1)}%
                          </p>
                        </div>
                      </div>
                      <p className="mt-3 text-xs text-on-surface/80">
                        Calculated automatically from harga dasar and harga jual.
                      </p>
                    </div>
                  )}

                  <div className="space-y-2">
                    <label className="text-xs font-semibold uppercase tracking-wider text-[#E5E2E1]" htmlFor="description">
                      Deskripsi tambahan
                    </label>
                    <textarea
                      className="w-full bg-[#1C1B1B] border border-outline-variant/20 hover:border-outline-variant/50 focus:border-primary focus:ring-1 focus:ring-primary/20 rounded-md px-4 py-3 text-sm text-on-surface transition-all placeholder:text-[#8B90A0]/50 outline-none resize-none scrollbar-thin scrollbar-thumb-outline-variant/30"
                      name="description"
                      onChange={handleChange}
                      placeholder="Penjelasan singkat atau informasi yang membantu."
                      rows={6}
                      value={formData.description}
                    />
                  </div>
                </div>
              </section>

              <section className="bg-surface-container-low p-8 rounded-lg shadow-sm">
                <div className="flex items-center gap-2 mb-8">
                  <span className="material-symbols-outlined text-primary text-lg">category</span>
                  <h3 className="text-sm font-bold font-headline uppercase tracking-widest text-on-surface-variant">
                    Klasifikasi Barang
                  </h3>
                </div>

                <div className="grid grid-cols-1 gap-6">
                  <div className="space-y-2">
                    <label className="text-xs font-semibold uppercase tracking-wider text-[#E5E2E1] block mb-2">
                      Kategori
                    </label>
                    <div className="flex items-center gap-4">
                      <div className="relative flex-1">
                        <select
                          className="w-full bg-[#1C1B1B] border border-outline-variant/20 hover:border-outline-variant/50 focus:border-primary text-sm py-3 px-4 rounded-md appearance-none focus:ring-1 focus:ring-primary/20 cursor-pointer transition-all outline-none"
                          name="category"
                          onChange={handleChange}
                          value={formData.category}
                        >
                          <option value="">Pilih Kategori</option>
                          {categories.map((item) => (
                            <option key={item.categoryCode} value={item.categoryName}>
                              {item.categoryName}
                            </option>
                          ))}
                        </select>
                        <span className="absolute right-4 top-1/2 -translate-y-1/2 material-symbols-outlined text-on-surface-variant pointer-events-none">
                          expand_more
                        </span>
                      </div>
                      <button
                        className="flex items-center justify-center gap-2 px-6 py-3 bg-[#1C1B1B] hover:bg-surface-container-high border border-outline-variant/20 hover:border-primary/50 text-sm font-semibold text-primary rounded-md transition-all whitespace-nowrap"
                        type="button"
                      >
                        <span className="material-symbols-outlined text-[16px]">add_circle</span>
                        Buat Baru
                      </button>
                    </div>
                  </div>

                  <div className="space-y-2">
                    <label className="text-xs font-semibold uppercase tracking-wider text-[#E5E2E1] block mb-2">
                      Supplier barang
                    </label>
                    <div className="flex items-center gap-4">
                      <div className="relative flex-1">
                        <select
                          className="w-full bg-[#1C1B1B] border border-outline-variant/20 hover:border-outline-variant/50 focus:border-primary text-sm py-3 px-4 rounded-md appearance-none focus:ring-1 focus:ring-primary/20 cursor-pointer transition-all outline-none"
                          name="supplier"
                          onChange={handleChange}
                          value={formData.supplier}
                        >
                          <option value="">Pilih Supplier</option>
                          {suppliers.map((item) => (
                            <option key={item.supplierCode} value={item.supplierName}>
                              {item.supplierName}
                            </option>
                          ))}
                        </select>
                        <span className="absolute right-4 top-1/2 -translate-y-1/2 material-symbols-outlined text-on-surface-variant pointer-events-none">
                          expand_more
                        </span>
                      </div>
                      <button
                        className="flex items-center justify-center gap-2 px-6 py-3 bg-[#1C1B1B] hover:bg-surface-container-high border border-outline-variant/20 hover:border-primary/50 text-sm font-semibold text-primary rounded-md transition-all whitespace-nowrap"
                        type="button"
                      >
                        <span className="material-symbols-outlined text-[16px]">add_circle</span>
                        Buat Baru
                      </button>
                    </div>
                  </div>

                  <div className="space-y-2">
                    <label className="text-xs font-semibold uppercase tracking-wider text-[#E5E2E1] block mb-2">
                      Brand
                    </label>
                    <div className="flex items-center gap-4">
                      <div className="relative flex-1">
                        <select
                          className="w-full bg-[#1C1B1B] border border-outline-variant/20 hover:border-outline-variant/50 focus:border-primary text-sm py-3 px-4 rounded-md appearance-none focus:ring-1 focus:ring-primary/20 cursor-pointer transition-all outline-none"
                          name="brand"
                          onChange={handleChange}
                          value={formData.brand}
                        >
                          <option value="">Pilih Brand</option>
                          {brands.map((item) => (
                            <option key={item.brandCode} value={item.brandName}>
                              {item.brandName}
                            </option>
                          ))}
                        </select>
                        <span className="absolute right-4 top-1/2 -translate-y-1/2 material-symbols-outlined text-on-surface-variant pointer-events-none">
                          expand_more
                        </span>
                      </div>
                      <button
                        className="flex items-center justify-center gap-2 px-6 py-3 bg-[#1C1B1B] hover:bg-surface-container-high border border-outline-variant/20 hover:border-primary/50 text-sm font-semibold text-primary rounded-md transition-all whitespace-nowrap"
                        type="button"
                      >
                        <span className="material-symbols-outlined text-[16px]">add_circle</span>
                        Buat Baru
                      </button>
                    </div>
                  </div>
                </div>
              </section>
            </div>

            <div className="col-span-4 space-y-8">
              <section className="bg-surface-container-low p-6 rounded-lg shadow-sm">
                <h3 className="text-[10px] font-bold font-headline uppercase tracking-widest text-on-surface-variant mb-4">
                  Quick Navigation
                </h3>
                <div className="space-y-3">
                  <a
                    className="group flex items-center justify-between p-4 bg-surface-container hover:bg-surface-container-high border border-outline-variant/10 hover:border-primary/30 rounded-md transition-all duration-200"
                    href="#"
                  >
                    <div className="flex items-center gap-3">
                      <span className="material-symbols-outlined text-on-surface-variant group-hover:text-primary transition-colors">
                        category
                      </span>
                      <span className="text-sm font-medium text-on-surface group-hover:text-primary transition-colors">
                        Manage Categories
                      </span>
                    </div>
                    <span className="material-symbols-outlined text-xs text-on-surface-variant opacity-0 group-hover:opacity-100 transition-all transform translate-x-[-4px] group-hover:translate-x-0">
                      arrow_forward_ios
                    </span>
                  </a>
                  <a
                    className="group flex items-center justify-between p-4 bg-surface-container hover:bg-surface-container-high border border-outline-variant/10 hover:border-primary/30 rounded-md transition-all duration-200"
                    href="#"
                  >
                    <div className="flex items-center gap-3">
                      <span className="material-symbols-outlined text-on-surface-variant group-hover:text-primary transition-colors">
                        factory
                      </span>
                      <span className="text-sm font-medium text-on-surface group-hover:text-primary transition-colors">
                        Manage Suppliers
                      </span>
                    </div>
                    <span className="material-symbols-outlined text-xs text-on-surface-variant opacity-0 group-hover:opacity-100 transition-all transform translate-x-[-4px] group-hover:translate-x-0">
                      arrow_forward_ios
                    </span>
                  </a>
                  <a
                    className="group flex items-center justify-between p-4 bg-surface-container hover:bg-surface-container-high border border-outline-variant/10 hover:border-primary/30 rounded-md transition-all duration-200"
                    href="#"
                  >
                    <div className="flex items-center gap-3">
                      <span className="material-symbols-outlined text-on-surface-variant group-hover:text-primary transition-colors">
                        verified
                      </span>
                      <span className="text-sm font-medium text-on-surface group-hover:text-primary transition-colors">
                        Manage Brands
                      </span>
                    </div>
                    <span className="material-symbols-outlined text-xs text-on-surface-variant opacity-0 group-hover:opacity-100 transition-all transform translate-x-[-4px] group-hover:translate-x-0">
                      arrow_forward_ios
                    </span>
                  </a>
                </div>
              </section>

              <section className="bg-surface-container p-6 rounded-lg shadow-sm border-l-2 border-primary">
                <h3 className="text-[10px] font-bold font-headline uppercase tracking-widest text-on-surface-variant mb-6">
                  Stock Status
                </h3>
                <div className="space-y-6">
                  <div className="flex justify-between items-center">
                    <span className="text-sm font-medium">Initial Stock Level</span>
                    <div className="flex items-center bg-surface-container-low rounded-md p-1">
                      <input
                        className="w-16 bg-transparent border-none text-center text-sm font-bold focus:ring-0"
                        min="0"
                        name="stock"
                        onChange={handleStockChange}
                        type="number"
                        value={formData.stock}
                      />
                    </div>
                  </div>

                  <div className="pt-4 border-t border-outline-variant/10">
                    <div className="flex justify-between items-center mb-2">
                      <span className="text-xs font-medium text-on-surface-variant">Low Stock Alert</span>
                      <label className="relative inline-flex items-center cursor-pointer">
                        <input defaultChecked className="sr-only peer" type="checkbox" value="" />
                        <div className="w-9 h-5 bg-surface-container-highest peer-focus:outline-none rounded-full peer peer-checked:after:translate-x-full rtl:peer-checked:after:-translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:start-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-4 after:w-4 after:transition-all peer-checked:bg-primary"></div>
                      </label>
                    </div>
                    <input
                      className="w-full bg-surface-container-low border-none rounded-sm py-2 px-3 text-xs focus:ring-1 focus:ring-primary"
                      readOnly
                      type="number"
                      value="5"
                    />
                  </div>

                  <div className="pt-2">
                    <div className="flex items-center gap-2 p-3 bg-surface-container-low rounded-md border border-outline-variant/10">
                      <span
                        className="material-symbols-outlined text-secondary"
                        style={{ fontVariationSettings: "'FILL' 1" }}
                      >
                        stars
                      </span>
                      <div className="flex flex-col">
                        <span className="text-xs font-semibold text-on-surface-variant uppercase">
                          Quality Control
                        </span>
                        <span className="text-[11px] text-on-surface">Verification Pending</span>
                      </div>
                    </div>
                  </div>
                </div>
              </section>

              <div className="px-2 space-y-4">
                <div className="flex justify-between text-xs font-semibold tracking-widest text-on-surface-variant uppercase">
                  <span>Created By</span>
                  <span className="text-on-surface">Alex Mercer</span>
                </div>
                <div className="flex justify-between text-xs font-semibold tracking-widest text-on-surface-variant uppercase">
                  <span>Date</span>
                  <span className="text-on-surface">Oct 24, 2023</span>
                </div>
                <div className="flex justify-between text-xs font-semibold tracking-widest text-on-surface-variant uppercase">
                  <span>Warehouse</span>
                  <span className="text-on-surface">Zone Delta-9</span>
                </div>
              </div>
            </div>
          </div>

          <div className="mt-16 flex items-center justify-between py-6 border-t border-outline-variant/10">
            <div className="flex items-center gap-4 text-on-surface-variant">
              <span className="material-symbols-outlined text-sm">history</span>
              <span className="text-xs font-medium">Last autosaved at 14:02:44</span>
            </div>
            <div className="flex items-center gap-4">
              {submitFeedback && (
                <p
                  aria-live="polite"
                  className={`text-sm font-medium ${submitFeedback.tone === "success" ? "text-emerald-300" : "text-red-300"}`}
                >
                  {submitFeedback.message}
                </p>
              )}
              <button
                className="px-6 py-2.5 text-sm font-semibold text-on-surface-variant hover:text-on-surface transition-colors"
                type="button"
              >
                Save as Draft
              </button>
              <button
                className="px-10 py-3 text-sm font-bold text-on-primary-container kinetic-gradient-primary rounded-md shadow-xl shadow-primary/20 hover:scale-[1.02] active:scale-95 transition-all disabled:cursor-not-allowed disabled:opacity-70"
                disabled={isSubmitting}
                type="submit"
              >
                {isSubmitting ? "Menyimpan..." : "Complete & Add Product"}
              </button>
            </div>
          </div>
        </form>

        <div className="fixed bottom-8 left-1/2 -translate-x-1/2 bg-surface-container-highest border-l-4 border-primary px-6 py-3 rounded-md shadow-2xl flex items-center gap-4 opacity-0 pointer-events-none transform translate-y-10 transition-all">
          <span className="material-symbols-outlined text-primary">check_circle</span>
          <span className="text-sm font-medium">Product draft saved successfully.</span>
        </div>
      </main>
    </div>
  );
}

export default Product;
