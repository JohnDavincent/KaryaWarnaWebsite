
import React, { useState } from "react";
import { login } from "../fetch/Authentication";

export default function Login() {

  const [formData, setFormData] = useState({
    email: "",
    password: ""
  })

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    })
  }

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    await login(formData)
  }

  return (
    <div className="bg-background text-on-surface font-body min-h-screen flex flex-col selection:bg-primary/30">
      <header className="fixed top-0 w-full h-16 flex justify-between items-center px-8 z-50">
        <div className="flex items-center gap-2">
          <div className="w-7 h-7 bg-primary rounded-sm flex items-center justify-center">
            <span className="text-on-primary font-black text-sm">K</span>
          </div>
          <span className="text-sm font-bold tracking-widest text-on-surface uppercase font-headline">
            KaryaWarna
          </span>
        </div>
        <div className="flex items-center gap-6">
          <span className="material-symbols-outlined text-on-surface-variant/50 cursor-pointer hover:text-primary transition-colors text-xl">
            language
          </span>
          <span className="material-symbols-outlined text-on-surface-variant/50 cursor-pointer hover:text-primary transition-colors text-xl">
            help
          </span>
        </div>
      </header>
      <div className="flex-grow flex items-center justify-center px-6 py-24">
        <div className="w-full max-w-5xl flex flex-col items-center">
          <div className="mb-16 text-center">
            <div className="inline-block relative">
              <span className="block text-[0.6rem] font-bold tracking-[0.4em] text-primary uppercase mb-4 opacity-80">
                Toko Bangunan dan Cat
              </span>
              <h1 className="font-headline text-6xl md:text-8xl font-black tracking-tighter text-on-surface leading-none">
                KaryaWarna
                <br />
                <span className="relative inline-block px-2 text-primary text-glow-highlight">
                  ERP
                </span>
              </h1>
            </div>
          </div>
        </div>
        <div className="w-full max-w-md">
          <div className="bg-surface-container border border-outline-variant/5 rounded-xl p-10 md:p-12 shadow-2xl">
            <div className="mb-10 text-center">
              <h2 className="font-headline text-xl font-bold text-on-surface mb-2">
                LOGIN
              </h2>
              <p className="text-on-surface-variant text-xs font-light tracking-wide">
                login with the available account
              </p>
            </div>
            <form className="space-y-10" onSubmit={handleSubmit}>
              {/*<!-- Email Input -->*/}
              <div className="relative floating-input">
                <input
                  className="w-full bg-transparent border-0 border-b border-outline-variant/30 px-0 py-3 text-on-surface focus:ring-0 focus:border-primary transition-all peer"
                  name="email"
                  value={formData.email}
                  onChange={handleChange}
                  type="email"
                  required

                />
                <label
                  className="absolute left-0 top-3 text-on-surface-variant/60 text-sm tracking-wide"
                  htmlFor="email"
                >
                  Email Address
                </label>
              </div>
              {/*<!-- Password Input -->*/}
              <div className="relative floating-input">
                <input
                  className="w-full bg-transparent border-0 border-b border-outline-variant/30 px-0 py-3 text-on-surface focus:ring-0 focus:border-primary transition-all peer"
                  name="password"
                  value={formData.password}
                  onChange={handleChange}
                  type="password"
                />
                <label
                  className="absolute left-0 top-3 text-on-surface-variant/60 text-sm tracking-wide"
                  htmlFor="password"
                >
                  Password
                </label>
                <span className="material-symbols-outlined absolute right-0 top-3 text-on-surface-variant/40 cursor-pointer hover:text-primary text-lg">
                  visibility
                </span>
              </div>
              <div className="flex items-center justify-between pt-2">
                <label className="flex items-center space-x-2 cursor-pointer group">
                  <input
                    className="h-3.5 w-3.5 rounded-sm border-outline-variant/30 bg-transparent text-primary focus:ring-0 focus:ring-offset-0 transition-all"
                    type="checkbox"
                  />
                  <span className="text-[0.65rem] text-on-surface-variant/60 font-label uppercase tracking-widest group-hover:text-on-surface transition-colors">
                    Remember
                  </span>
                </label>
                <a
                  className="text-[0.65rem] text-primary/70 font-label uppercase tracking-widest hover:text-primary transition-colors"
                  href="#"
                >
                  Recovery
                </a>
              </div>
              <button
                className="w-full py-4 bg-primary text-on-primary font-headline font-extrabold text-xs uppercase tracking-[0.2em] rounded-sm hover:brightness-110 active:scale-[0.98] transition-all shadow-lg shadow-primary/10"
                type="submit"
              >
                Login
              </button>
            </form>
          </div>
          <p className="mt-12 text-center text-[0.6rem] text-on-surface-variant/40 font-label uppercase tracking-[0.3em]">
            New operator?{" "}
            <a
              className="text-primary/70 hover:text-primary transition-colors"
              href="#"
            >
              Request
            </a>
          </p>
        </div>
      </div>
    </div>
  );
}
