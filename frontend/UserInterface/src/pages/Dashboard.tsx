import { useEffect } from "react";
import Navbar from "../components/Navbar";
import { getTransaction } from "../fetch/transactionList";
import { useState } from "react";

function Dashboard() {

  const [transactions, setTransactions] = useState<any[]>([]);

  useEffect(() => {
    const fetchTransaction = async () => {
      const result = await getTransaction();
      setTransactions(result.data)
    };
    fetchTransaction();
  }, [])


  return (
    <div className="font-body bg-background text-on-background selection:bg-primary/30 min-h-screen">
      <Navbar />
      <header className="fixed top-0 right-0 left-20 z-40 flex justify-between items-center px-8 docked full-width top-0 h-16 bg-[#131313]/80 backdrop-blur-xl border-b border-[#414754]/15">
        <div className="flex items-center gap-6">
          <h1 className="font-headline font-bold text-lg text-on-surface tracking-tight">
            Karya Warna
          </h1>
          <div className="relative group">
            <span
              className="absolute left-3 top-1/2 -translate-y-1/2 text-on-surface-variant material-symbols-outlined text-sm"
              data-icon="search"
            >
              search
            </span>
            <input
              className="bg-surface-container-low border-none text-xs pl-10 pr-4 py-2 w-64 focus:ring-1 focus:ring-primary rounded-sm transition-all"
              placeholder="Global Search..."
              type="text"
            />
          </div>
        </div>
        <div className="flex items-center gap-6">
          <button className="text-[#C1C6D7] hover:text-[#5AF8FB] transition-colors cursor-pointer relative">
            <span className="material-symbols-outlined" data-icon="notifications">
              notifications
            </span>
            <span className="absolute top-0 right-0 w-2 h-2 bg-primary rounded-full border border-surface"></span>
          </button>
          <button className="text-[#C1C6D7] hover:text-[#5AF8FB] transition-colors cursor-pointer">
            <span className="material-symbols-outlined" data-icon="help_outline">
              help_outline
            </span>
          </button>
          <div className="h-4 w-px bg-outline-variant/30"></div>
          <div className="flex items-center gap-3">
            <div className="text-right hidden sm:block">
              <p className="text-xs font-headline font-medium text-on-surface">
                Alex Mercer
              </p>
              <p className="text-[10px] text-on-surface-variant uppercase tracking-widest">
                Executive Director
              </p>
            </div>
          </div>
        </div>
      </header>

      {/*<!-- Main Content Canvas -->*/}
      <main className="ml-20 pt-20 p-8 min-h-screen">
        {/*<!-- Top Row: Quick Stats -->*/}
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-10">
          {/*<!-- Today's Revenue -->*/}
          <div className="bg-surface-container-low p-6 rounded-sm flex flex-col justify-between border-l-2 border-primary group hover:bg-surface-container transition-colors">
            <p className="text-[10px] font-label font-medium uppercase tracking-widest text-on-surface-variant mb-2">Today's Revenue</p>
            <div className="flex items-baseline gap-2">
              <h2 className="text-3xl font-headline font-bold text-on-surface">$42,850</h2>
              <span className="text-[10px] font-label font-bold text-primary flex items-center gap-0.5">
                <span className="material-symbols-outlined text-xs" data-icon="trending_up">trending_up</span> 12%
              </span>
            </div>
          </div>
          {/* Low Stock Items (Alert) */}
          <div className="bg-surface-container-low p-6 rounded-sm flex flex-col justify-between border-l-2 border-tertiary-container group hover:bg-surface-container transition-colors">
            <p className="text-[10px] font-label font-medium uppercase tracking-widest text-on-surface-variant mb-2">Stock Alerts</p>
            <div className="flex items-baseline gap-2">
              <h2 className="text-3xl font-headline font-bold text-on-surface">14</h2>
              <span className="text-[10px] font-label font-bold text-tertiary-container">CRITICAL</span>
            </div>
          </div>
          {/*<!-- Total Employees Present -->*/}
          <div className="bg-surface-container-low p-6 rounded-sm flex flex-col justify-between border-l-2 border-secondary group hover:bg-surface-container transition-colors">
            <p className="text-[10px] font-label font-medium uppercase tracking-widest text-on-surface-variant mb-2">On-Site Workforce</p>
            <div className="flex items-baseline gap-2">
              <h2 className="text-3xl font-headline font-bold text-on-surface">128</h2>
              <span className="text-[10px] font-label font-medium text-on-surface-variant">/ 140 STAFF</span>
            </div>
          </div>
          {/*<!-- Pending Orders -->*/}
          <div className="bg-surface-container-low p-6 rounded-sm flex flex-col justify-between border-l-2 border-outline group hover:bg-surface-container transition-colors">
            <p className="text-[10px] font-label font-medium uppercase tracking-widest text-on-surface-variant mb-2">Pending Orders</p>
            <div className="flex items-baseline gap-2">
              <h2 className="text-3xl font-headline font-bold text-on-surface">34</h2>
              <span className="text-[10px] font-label font-medium text-on-surface-variant">AWAITING SHIPMENT</span>
            </div>
          </div>
        </div>
        {/*<!-- Middle Row -->*/}
        <div className="grid grid-cols-1 lg:grid-cols-10 gap-8 mb-10">
          {/* Left: Daily Transactions Table (60%) */}
          <div className="lg:col-span-6 bg-surface-container-low p-6 rounded-sm">
            <div className="flex justify-between items-center mb-6">
              <h3 className="font-headline font-medium text-lg">Daily Transactions</h3>
              <button className="bg-gradient-to-br from-primary to-primary-container text-on-primary-container px-4 py-2 rounded-md text-xs font-semibold flex items-center gap-2 hover:opacity-90 transition-all scale-95 active:scale-90">
                <span className="material-symbols-outlined text-sm" data-icon="add">add</span>
                Add New Transaction
              </button>
            </div>
            <div className="overflow-x-auto">
              <table className="w-full text-left border-collapse">
                <thead>
                  <tr className="border-b border-outline-variant/15">
                    <th className="py-3 text-[10px] font-label font-medium uppercase tracking-widest text-on-surface-variant">Transaction Number</th>
                    <th className="py-3 text-[10px] font-label font-medium uppercase tracking-widest text-on-surface-variant">Grand total</th>
                    <th className="py-3 text-[10px] font-label font-medium uppercase tracking-widest text-on-surface-variant">Modified by</th>
                    <th className="py-3 text-[10px] font-label font-medium uppercase tracking-widest text-on-surface-variant">Date</th>
                    <th className="py-3 text-[10px] font-label font-medium uppercase tracking-widest text-on-surface-variant">Status</th>
                  </tr>
                </thead>
                <tbody className="text-xs font-body">
                  {transactions.map((item, index) => (
                    <tr
                      key={index}
                      className="group hover:bg-surface-bright transition-colors cursor-pointer border-b border-outline-variant/10"
                    >
                      <td className="py-4 text-on-surface-variant font-medium">{item.order_number}</td>
                      <td className="py-4 text-on-surface">{item.grand_total}</td>
                      <td className="py-4 text-on-surface">{item.modified_by}</td>
                      <td className="py-4 text-on-surface-variant">{item.status == "SUCCESS" ? item.update_at : item.created_at}</td>
                      <td className="py-4">
                        <span className="bg-primary/10 text-primary-fixed px-2 py-0.5 rounded-full text-[10px] font-bold">
                          {item.status}
                        </span>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          </div>
          <div className="lg:col-span-4 bg-surface-container-low p-6 rounded-sm">
            <h3 className="font-headline font-medium text-lg mb-6">Stock Update Feed</h3>
            <div className="flex flex-col gap-4">
              <div className="p-3 bg-surface-container rounded-sm flex items-center justify-between group hover:bg-surface-container-high transition-all">
                <div className="flex items-center gap-3">
                  <div className="w-10 h-10 bg-surface-container-highest rounded-sm flex items-center justify-center">
                    <span className="material-symbols-outlined text-primary" data-icon="devices">devices</span>
                  </div>
                  <div>
                    <p className="text-xs font-semibold text-on-surface">X-Pro Monitor 32"</p>
                    <p className="text-[10px] text-on-surface-variant">Warehouse A • Electronics</p>
                  </div>
                </div>
                <div className="text-right">
                  <p className="text-xs font-bold text-primary">+12</p>
                  <p className="text-[10px] text-on-surface-variant">UNITS</p>
                </div>
              </div>
              <div className="p-3 bg-surface-container rounded-sm flex items-center justify-between group hover:bg-surface-container-high transition-all">
                <div className="flex items-center gap-3">
                  <div className="w-10 h-10 bg-surface-container-highest rounded-sm flex items-center justify-center">
                    <span className="material-symbols-outlined text-tertiary" data-icon="inventory">inventory</span>
                  </div>
                  <div>
                    <p className="text-xs font-semibold text-on-surface">ErgoDesk Frame</p>
                    <p className="text-[10px] text-on-surface-variant">Warehouse B • Furniture</p>
                  </div>
                </div>
                <div className="text-right">
                  <p className="text-xs font-bold text-tertiary">-5</p>
                  <p className="text-[10px] text-on-surface-variant">LOW STOCK</p>
                </div>
              </div>
              <div className="p-3 bg-surface-container rounded-sm flex items-center justify-between group hover:bg-surface-container-high transition-all">
                <div className="flex items-center gap-3">
                  <div className="w-10 h-10 bg-surface-container-highest rounded-sm flex items-center justify-center">
                    <span className="material-symbols-outlined text-secondary" data-icon="cable">cable</span>
                  </div>
                  <div>
                    <p className="text-xs font-semibold text-on-surface">Type-C Power Cable</p>
                    <p className="text-[10px] text-on-surface-variant">Warehouse A • Acc.</p>
                  </div>
                </div>
                <div className="text-right">
                  <p className="text-xs font-bold text-primary">+45</p>
                  <p className="text-[10px] text-on-surface-variant">RESTOCKED</p>
                </div>
              </div>
              <div className="p-3 bg-surface-container rounded-sm flex items-center justify-between group hover:bg-surface-container-high transition-all">
                <div className="flex items-center gap-3">
                  <div className="w-10 h-10 bg-surface-container-highest rounded-sm flex items-center justify-center">
                    <span className="material-symbols-outlined text-on-surface-variant" data-icon="print">print</span>
                  </div>
                  <div>
                    <p className="text-xs font-semibold text-on-surface">LaserJet 500 Toner</p>
                    <p className="text-[10px] text-on-surface-variant">Warehouse C • Supplies</p>
                  </div>
                </div>
                <div className="text-right">
                  <p className="text-xs font-bold text-on-surface">0</p>
                  <p className="text-[10px] text-error font-bold">OUT OF STOCK</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </main>
    </div>

  );
}

export default Dashboard;
