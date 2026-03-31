import React from "react";

function Navbar() {
  return (
    <aside className="fixed left-0 top-0 h-full z-50 flex flex-col items-center py-6 h-screen w-20 flex flex-col border-r border-[#414754]/15 bg-[#0E0E0E] font-['Manrope'] tracking-tight text-sm">
      <div className="mb-10 text-xl font-bold text-[#2DDBDE] tracking-tighter">
        K.
      </div>
      <nav className="flex flex-col gap-4 w-full items-center">
        {/* Active: Dashboard*/}
        <a
          className="group relative flex items-center justify-center w-12 h-12 bg-[#201F1F] text-[#2DDBDE] border-l-2 border-[#2DDBDE] scale-95 active:scale-90 transition-all duration-200"
          href="#"
        >
          <span className="material-symbols-outlined" data-icon="dashboard">
            dashboard
          </span>
        </a>
        <a
          className="group flex items-center justify-center w-12 h-12 text-[#C1C6D7] opacity-70 hover:bg-[#1C1B1B] hover:text-[#2DDBDE] hover:opacity-100 scale-95 active:scale-90 transition-all duration-200"
          href="#"
        >
          <span className="material-symbols-outlined" data-icon="inventory_2">
            inventory_2
          </span>
        </a>
        <a
          className="group flex items-center justify-center w-12 h-12 text-[#C1C6D7] opacity-70 hover:bg-[#1C1B1B] hover:text-[#2DDBDE] hover:opacity-100 scale-95 active:scale-90 transition-all duration-200"
          href="#"
        >
          <span className="material-symbols-outlined" data-icon="groups">
            groups
          </span>
        </a>
        <a
          className="group flex items-center justify-center w-12 h-12 text-[#C1C6D7] opacity-70 hover:bg-[#1C1B1B] hover:text-[#2DDBDE] hover:opacity-100 scale-95 active:scale-90 transition-all duration-200"
          href="#"
        >
          <span className="material-symbols-outlined" data-icon="receipt_long">
            receipt_long
          </span>
        </a>
        <a
          className="group flex items-center justify-center w-12 h-12 text-[#C1C6D7] opacity-70 hover:bg-[#1C1B1B] hover:text-[#2DDBDE] hover:opacity-100 scale-95 active:scale-90 transition-all duration-200"
          href="#"
        >
          <span className="material-symbols-outlined" data-icon="settings">
            settings
          </span>
        </a>
      </nav>
      <div className="mt-auto">
        <div className="w-8 h-8 rounded-sm bg-surface-container-highest overflow-hidden">
          <img
            className="w-full h-full object-cover"
            data-alt="close-up portrait of a professional male executive in a dark suit with neutral background and sharp lighting"
            src="https://lh3.googleusercontent.com/aida-public/AB6AXuDUnogIJWDWCPX61SKglP1aVcaACj3vmuKmSJmym9oSV2TapYII373JLNLszY8CHlv1rvG0tHxr_pe8LVO6WJn2On1jt9fuIdW6767OBTiEMX8D0I0ZyiARQVhWQ_Ey1TwGDV9Ci1cQbZXKkPhInBTL-npxnrpMDakEyO07AL3XCV0eoCOzRhjLmDX2LYS0G5KdBPqOIB4bAc27SwGr093siQ4xPsFcWrp3S_MIGPjpeJTNmVcDVIjvJHgU5Wnn3-85rNI2ra6iATy6"
          />
        </div>
      </div>
    </aside>
  );
}

export default Navbar;
