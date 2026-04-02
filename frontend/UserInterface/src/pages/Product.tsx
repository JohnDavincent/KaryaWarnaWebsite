import { useEffect, useState } from "react";
import Navbar from "../components/Navbar";
import { addProduct } from "../fetch/inventory";

function Product() {

  const [formData, setFormData] = useState({
    productName: "",
    purchasedPrice: 0,
    sellPrice: 0,
    brand: "",
    category: "",
    supplier: "",
    stock: 0,
    description: ""
  })

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    })
  }

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    console.log(formData)
    await addProduct(formData)
  }

  return (
    <main className="ml-64 pt-24 pb-16 px-12 max-w-7xl">
      <Navbar />
      <div className="flex justify-between items-end mb-10">
        <div>
          <span className="text-[10px] font-bold tracking-[0.2em] text-primary uppercase font-label">
            Pengaturan inventoris
          </span>
          <h2 className="text-4xl font-extrabold font-headline tracking-tight text-on-surface mt-1">
            Tambah Produk
          </h2>
        </div>
        <div className="flex gap-4">
          <button className="px-6 py-2.5 text-sm font-semibold text-on-surface-variant hover:bg-surface-container-high transition-colors rounded-md">
            Discard
          </button>
          <button className="px-8 py-2.5 text-sm font-bold text-on-primary-container kinetic-gradient-primary rounded-md shadow-lg shadow-primary/10">
            Tambahkan Produk
          </button>
        </div>
      </div>
      {/* Form Layout (Bento Grid Inspired) */}
      <div className="grid grid-cols-12 gap-8">
        {/* Left Column: Core Identity */}
        <div className="col-span-8 space-y-8">
          <section className="bg-surface-container-low p-8 rounded-lg shadow-sm">
            <div className="flex items-center gap-2 mb-8">
              <span className="material-symbols-outlined text-primary text-lg">
                info
              </span>
              <h3 className="text-sm font-bold font-headline uppercase tracking-widest text-on-surface-variant">
                General Information
              </h3>
            </div>
            <div className="space-y-10">
              <div className="relative group">
                <input
                  className="peer w-full bg-transparent border-none border-b ghost-border-bottom focus:border-primary focus:ring-0 px-0 py-3 text-lg font-medium text-on-surface transition-all"
                  id="product_name"
                  placeholder=" "
                  type="text"
                  name="productName"
                  value={formData.productName}
                  onChange={handleChange}
                  required

                />
                <label
                  className="absolute left-0 top-3 text-on-surface-variant text-sm font-medium pointer-events-none transition-all duration-200"
                  htmlFor="product_name"

                >
                  Nama Barang
                </label>
              </div>
              <div className="grid grid-cols-2 gap-8">
                <div className="relative group">
                  <input
                    className="peer w-full bg-transparent border-none border-b ghost-border-bottom focus:border-primary focus:ring-0 px-0 py-3 text-sm text-on-surface transition-all"
                    id="base_price"
                    placeholder=" "
                    type="number"
                    name="purchasedPrice"
                    value={formData.purchasedPrice}
                    onChange={handleChange}
                    required

                  />
                  <label
                    className="absolute left-0 top-3 text-on-surface-variant text-[10px] uppercase font-bold tracking-wider pointer-events-none transition-all duration-200"
                    htmlFor="sku"
                  >
                    Harga dasar (Rp)
                  </label>
                </div>
                <div className="relative group">
                  <input
                    className="peer w-full bg-transparent border-none border-b ghost-border-bottom focus:border-primary focus:ring-0 px-0 py-3 text-sm text-on-surface transition-all"
                    id="Sellprice"
                    placeholder=" "
                    type="number"
                    name="sellPrice"
                    value={formData.sellPrice}
                    onChange={handleChange}
                  />
                  <label
                    className="absolute left-0 top-3 text-on-surface-variant text-[10px] uppercase font-bold tracking-wider pointer-events-none transition-all duration-200"
                    htmlFor="price"
                  >
                    Harga Jual (Rp)
                  </label>
                </div>
              </div>
              <div className="relative group">
                <textarea
                  className="peer w-full bg-transparent border-none border-b ghost-border-bottom focus:border-primary focus:ring-0 px-0 py-3 text-sm text-on-surface transition-all resize-none"
                  id="description"
                  placeholder=" "
                  rows={4}
                ></textarea>
                <input
                  name="description"
                  value={formData.description}
                  onChange={handleChange}
                  type="hidden"
                />
                <label
                  className="absolute left-0 top-3 text-on-surface-variant text-[10px] uppercase font-bold tracking-wider pointer-events-none transition-all duration-200"
                  htmlFor="description"
                >
                  Deskripsi Produk
                </label>
              </div>
            </div>
          </section>
          <section className="bg-surface-container-low p-8 rounded-lg shadow-sm">
            <div className="flex items-center gap-2 mb-8">
              <span className="material-symbols-outlined text-primary text-lg">
                category
              </span>
              <h3 className="text-sm font-bold font-headline uppercase tracking-widest text-on-surface-variant">
                Klasifikasi Barang
              </h3>
            </div>
            <div className="grid grid-cols-3 gap-6">
              {/*Custom Select with Add New */}
              <div className="space-y-2">
                <div className="flex justify-between items-center">
                  <label className="text-[10px] font-bold uppercase tracking-wider text-on-surface-variant">
                    Kategori
                  </label>
                  <button className="flex items-center gap-1 text-[10px] font-bold text-primary hover:underline">
                    <span className="material-symbols-outlined text-[12px]">
                      add_circle
                    </span>{" "}
                    Buat
                  </button>
                </div>
                <div className="relative">
                  <select className="w-full bg-surface-container border-none ghost-border-bottom text-xs py-3 pl-3 pr-8 rounded-sm appearance-none focus:ring-1 focus:ring-primary cursor-pointer">
                    <option>Pilih Kategori</option>
                    <option>Hardware</option>
                    <option>Software Licenses</option>
                    <option>Peripherals</option>
                  </select>
                  <span className="absolute right-2 top-1/2 -translate-y-1/2 material-symbols-outlined text-sm pointer-events-none">expand_more</span>
                </div>
              </div>
              <div className="space-y-2">
                <div className="flex justify-between items-center">
                  <label className="text-[10px] font-bold uppercase tracking-wider text-on-surface-variant">Supplier barang</label>
                  <button className="flex items-center gap-1 text-[10px] font-bold text-primary hover:underline">
                    <span className="material-symbols-outlined text-[12px]">add_circle</span> Buat
                  </button>
                </div>
                <div className="relative">
                  <select className="w-full bg-surface-container border-none ghost-border-bottom text-xs py-3 pl-3 pr-8 rounded-sm appearance-none focus:ring-1 focus:ring-primary cursor-pointer">
                    <option>Pilih Supplier</option>
                    <option>Global Tech Corp</option>
                    <option>Nexus Systems</option>
                    <option>Ironwood Logistics</option>
                  </select>
                  <span className="absolute right-2 top-1/2 -translate-y-1/2 material-symbols-outlined text-sm pointer-events-none">expand_more</span>
                </div>
              </div>
              <div className="space-y-2">
                <div className="flex justify-between items-center">
                  <label className="text-[10px] font-bold uppercase tracking-wider text-on-surface-variant">Brand</label>
                  <button className="flex items-center gap-1 text-[10px] font-bold text-primary hover:underline">
                    <span className="material-symbols-outlined text-[12px]">add_circle</span> Buat
                  </button>
                </div>
                <div className="relative">
                  <select className="w-full bg-surface-container border-none ghost-border-bottom text-xs py-3 pl-3 pr-8 rounded-sm appearance-none focus:ring-1 focus:ring-primary cursor-pointer">
                    <option>Select Brand</option>
                    <option>Kinetic Pro</option>
                    <option>Elite Series</option>
                    <option>Vanguard</option>
                  </select>
                  <span className="absolute right-2 top-1/2 -translate-y-1/2 material-symbols-outlined text-sm pointer-events-none">expand_more</span>
                </div>
              </div>
            </div>
          </section>
        </div>
        {/* Right Column: Media & Inventory Status */}
        <div className="col-span-4 space-y-8">
          {/* Media Upload */}
          <section className="bg-surface-container-low p-6 rounded-lg shadow-sm">
            <h3 className="text-[10px] font-bold font-headline uppercase tracking-widest text-on-surface-variant mb-4">Quick Navigation</h3>
            <div className="space-y-3">
              <a className="group flex items-center justify-between p-4 bg-surface-container hover:bg-surface-container-high border border-outline-variant/10 hover:border-primary/30 rounded-md transition-all duration-200" href="#">
                <div className="flex items-center gap-3">
                  <span className="material-symbols-outlined text-on-surface-variant group-hover:text-primary transition-colors">category</span>
                  <span className="text-sm font-medium text-on-surface group-hover:text-primary transition-colors">Manage Categories</span>
                </div>
                <span className="material-symbols-outlined text-xs text-on-surface-variant opacity-0 group-hover:opacity-100 transition-all transform translate-x-[-4px] group-hover:translate-x-0">arrow_forward_ios</span>
              </a>
              <a className="group flex items-center justify-between p-4 bg-surface-container hover:bg-surface-container-high border border-outline-variant/10 hover:border-primary/30 rounded-md transition-all duration-200" href="#">
                <div className="flex items-center gap-3">
                  <span className="material-symbols-outlined text-on-surface-variant group-hover:text-primary transition-colors">factory</span>
                  <span className="text-sm font-medium text-on-surface group-hover:text-primary transition-colors">Manage Suppliers</span>
                </div>
                <span className="material-symbols-outlined text-xs text-on-surface-variant opacity-0 group-hover:opacity-100 transition-all transform translate-x-[-4px] group-hover:translate-x-0">arrow_forward_ios</span>
              </a>
              <a className="group flex items-center justify-between p-4 bg-surface-container hover:bg-surface-container-high border border-outline-variant/10 hover:border-primary/30 rounded-md transition-all duration-200" href="#">
                <div className="flex items-center gap-3">
                  <span className="material-symbols-outlined text-on-surface-variant group-hover:text-primary transition-colors">verified</span>
                  <span className="text-sm font-medium text-on-surface group-hover:text-primary transition-colors">Manage Brands</span>
                </div>
                <span className="material-symbols-outlined text-xs text-on-surface-variant opacity-0 group-hover:opacity-100 transition-all transform translate-x-[-4px] group-hover:translate-x-0">arrow_forward_ios</span>
              </a>
            </div>
          </section>
          {/* Inventory Control */}
          <section className="bg-surface-container p-6 rounded-lg shadow-sm border-l-2 border-primary">
            <h3 className="text-[10px] font-bold font-headline uppercase tracking-widest text-on-surface-variant mb-6">Stock Status</h3>
            <div className="space-y-6">
              <div className="flex justify-between items-center">
                <span className="text-sm font-medium">Initial Stock Level</span>
                <div className="flex items-center bg-surface-container-low rounded-md p-1">
                  <button className="w-8 h-8 flex items-center justify-center hover:text-primary transition-colors">
                    <span className="material-symbols-outlined text-sm">remove</span>
                  </button>
                  <input className="w-12 bg-transparent border-none text-center text-sm font-bold focus:ring-0" type="number" value="0" />
                  <button className="w-8 h-8 flex items-center justify-center hover:text-primary transition-colors">
                    <span className="material-symbols-outlined text-sm">add</span>
                  </button>
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
                <input className="w-full bg-surface-container-low border-none rounded-sm py-2 px-3 text-xs focus:ring-1 focus:ring-primary" type="number" value="5" />
              </div>
              <div className="pt-2">
                <div className="flex items-center gap-2 p-3 bg-surface-container-low rounded-md border border-outline-variant/10">
                  <span className="material-symbols-outlined text-secondary" style={{ fontVariationSettings: "'FILL' 1" }}>stars</span>
                  <div className="flex flex-col">
                    <span className="text-[10px] font-bold text-on-surface-variant uppercase">Quality Control</span>
                    <span className="text-[11px] text-on-surface">Verification Pending</span>
                  </div>
                </div>
              </div>
            </div>
          </section>
          {/* Information Metadata */}
          <div className="px-2 space-y-4">
            <div className="flex justify-between text-[10px] font-bold tracking-widest text-on-surface-variant uppercase">
              <span>Created By</span>
              <span className="text-on-surface">Alex Mercer</span>
            </div>
            <div className="flex justify-between text-[10px] font-bold tracking-widest text-on-surface-variant uppercase">
              <span>Date</span>
              <span className="text-on-surface">Oct 24, 2023</span>
            </div>
            <div className="flex justify-between text-[10px] font-bold tracking-widest text-on-surface-variant uppercase">
              <span>Warehouse</span>
              <span className="text-on-surface">Zone Delta-9</span>
            </div>
          </div>
        </div>
      </div>
      {/* Footer Actions */}
      <div className="mt-16 flex items-center justify-between py-6 border-t border-outline-variant/10">
        <div className="flex items-center gap-4 text-on-surface-variant">
          <span className="material-symbols-outlined text-sm">history</span>
          <span className="text-xs font-medium">Last autosaved at 14:02:44</span>
        </div>
        <div className="flex gap-4">
          <button className="px-6 py-2.5 text-sm font-semibold text-on-surface-variant hover:text-on-surface transition-colors">Save as Draft</button>
          <button className="px-10 py-3 text-sm font-bold text-on-primary-container kinetic-gradient-primary rounded-md shadow-xl shadow-primary/20 hover:scale-[1.02] active:scale-95 transition-all">Complete &amp; Add Product</button>
        </div>
      </div>
      {/* Success Feedback (Ghost State) */}
      <div className="fixed bottom-8 left-1/2 -translate-x-1/2 bg-surface-container-highest border-l-4 border-primary px-6 py-3 rounded-md shadow-2xl flex items-center gap-4 opacity-0 pointer-events-none transform translate-y-10 transition-all">
        <span className="material-symbols-outlined text-primary">check_circle</span>
        <span className="text-sm font-medium">Product draft saved successfully.</span>
      </div>
    </main>
  );
}

export default Product;
