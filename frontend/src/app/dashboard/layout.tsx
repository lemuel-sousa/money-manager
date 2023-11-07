import { BadgeDollarSign } from "lucide-react";
import React from "react";

export default function DashboardLayout({ children } : { children: React.ReactNode }) {
    return (
        <>
            <div className="flex container items-center m-auto h-20 rounded-lg shadow-md gap-4 bg-zinc-800">
                <BadgeDollarSign className="text-emerald-400" size={48}/>
                <h1 className="uppercase font-bold text-3xl text-slate-100">Money Manager</h1>
            </div>
            <div className="container items-center w-md m-auto mt-2 rounded-lg shadow-md bg-zinc-50">
                {children}
            </div>
        </>
    );
}