function Navbar() {
  return (
    <aside className="peer group/sidebar fixed left-0 top-0 z-50 flex flex-col py-6 h-screen w-20 hover:w-64 border-r border-[#414754]/15 bg-[#0E0E0E] font-['Manrope'] tracking-tight text-sm transition-all duration-300 overflow-x-hidden">
      
      {/* Brand Logo */}
      <div className="mb-10 flex items-center h-12 w-full">
        <div className="w-20 flex justify-center shrink-0 text-xl font-bold text-[#2DDBDE] tracking-tighter">
          K.
        </div>
        <span className="whitespace-nowrap opacity-0 group-hover/sidebar:opacity-100 transition-opacity duration-300 flex-1 text-lg font-extrabold text-[#E5E2E1] tracking-wide">
          KaryaWarna
        </span>
      </div>

      {/* Navigation Links */}
      <nav className="flex flex-col gap-2 w-full">
        {/* Active: Dashboard*/}
        <a
          className="group relative flex items-center h-12 w-full bg-[#1C1B1B]/60 text-[#2DDBDE] border-l-2 border-[#2DDBDE] transition-all duration-200"
          href="/dashboard"
        >
          <div className="w-20 flex justify-center shrink-0">
            <span className="material-symbols-outlined text-[22px]" data-icon="dashboard">
              dashboard
            </span>
          </div>
          <span className="whitespace-nowrap opacity-0 group-hover/sidebar:opacity-100 transition-opacity duration-300 font-semibold tracking-wide">
            Dashboard
          </span>
        </a>

        {/* Inventory */}
        <a
          className="group relative flex items-center h-12 w-full text-[#C1C6D7] opacity-70 hover:bg-[#1C1B1B]/40 hover:text-[#2DDBDE] hover:opacity-100 transition-all duration-200 border-l-2 border-transparent hover:border-[#2DDBDE]/50"
          href="/product"
        >
          <div className="w-20 flex justify-center shrink-0">
            <span className="material-symbols-outlined text-[22px]" data-icon="inventory_2">
              inventory
            </span>
          </div>
          <span className="whitespace-nowrap opacity-0 group-hover/sidebar:opacity-100 transition-opacity duration-300 font-semibold tracking-wide">
            Inventoris
          </span>
        </a>

        {/* Users */}
        <a
          className="group relative flex items-center h-12 w-full text-[#C1C6D7] opacity-70 hover:bg-[#1C1B1B]/40 hover:text-[#2DDBDE] hover:opacity-100 transition-all duration-200 border-l-2 border-transparent hover:border-[#2DDBDE]/50"
          href="#"
        >
          <div className="w-20 flex justify-center shrink-0">
            <span className="material-symbols-outlined text-[22px]" data-icon="groups">
              groups
            </span>
          </div>
          <span className="whitespace-nowrap opacity-0 group-hover/sidebar:opacity-100 transition-opacity duration-300 font-semibold tracking-wide">
            Karyawan
          </span>
        </a>

        {/* Transactions */}
        <a
          className="group relative flex items-center h-12 w-full text-[#C1C6D7] opacity-70 hover:bg-[#1C1B1B]/40 hover:text-[#2DDBDE] hover:opacity-100 transition-all duration-200 border-l-2 border-transparent hover:border-[#2DDBDE]/50"
          href="#"
        >
          <div className="w-20 flex justify-center shrink-0">
            <span className="material-symbols-outlined text-[22px]" data-icon="receipt_long">
              receipt_long
            </span>
          </div>
          <span className="whitespace-nowrap opacity-0 group-hover/sidebar:opacity-100 transition-opacity duration-300 font-semibold tracking-wide">
            Transaksi
          </span>
        </a>

        {/* Settings */}
        <a
          className="group relative flex items-center h-12 w-full text-[#C1C6D7] opacity-70 hover:bg-[#1C1B1B]/40 hover:text-[#2DDBDE] hover:opacity-100 transition-all duration-200 border-l-2 border-transparent hover:border-[#2DDBDE]/50"
          href="#"
        >
          <div className="w-20 flex justify-center shrink-0">
            <span className="material-symbols-outlined text-[22px]" data-icon="settings">
              settings
            </span>
          </div>
          <span className="whitespace-nowrap opacity-0 group-hover/sidebar:opacity-100 transition-opacity duration-300 font-semibold tracking-wide">
            Pengaturan
          </span>
        </a>
      </nav>

      {/* User Info Profile */}
      <div className="mt-auto flex items-center w-full relative pt-6 border-t border-white/5">
        <div className="w-20 flex justify-center shrink-0">
          <div className="w-10 h-10 rounded-full border-2 border-[#1C1B1B] overflow-hidden">
            <img
              className="w-full h-full object-cover"
              alt="Profile"
              src="https://lh3.googleusercontent.com/aida-public/AB6AXuDUnogIJWDWCPX61SKglP1aVcaACj3vmuKmSJmym9oSV2TapYII373JLNLszY8CHlv1rvG0tHxr_pe8LVO6WJn2On1jt9fuIdW6767OBTiEMX8D0I0ZyiARQVhWQ_Ey1TwGDV9Ci1cQbZXKkPhInBTL-npxnrpMDakEyO07AL3XCV0eoCOzRhjLmDX2LYS0G5KdBPqOIB4bAc27SwGr093siQ4xPsFcWrp3S_MIGPjpeJTNmVcDVIjvJHgU5Wnn3-85rNI2ra6iATy6"
            />
          </div>
        </div>
        <div className="flex flex-col whitespace-nowrap opacity-0 group-hover/sidebar:opacity-100 transition-opacity duration-300">
           <span className="text-sm font-bold text-[#E5E2E1] tracking-wide">Alex Mercer</span>
           <span className="text-[11px] text-[#2DDBDE] uppercase tracking-widest font-semibold mt-0.5">Administrator</span>
        </div>
      </div>
    </aside>
  );
}

export default Navbar;
