'use client'

import { ActivityTableContext } from "@/context/activity-table-context";
import { useContext } from "react";

export default function ActivityBalance() {

      const activityTableContext = useContext(ActivityTableContext);

      const balance = activityTableContext.balance;

      const balanceFormated = balance.toLocaleString("pt-Br", {
            minimumFractionDigits: 2,
            maximumFractionDigits: 2
      });

      var balanceColor = "text-emerald-500";

      if ( balance < 0 ) balanceColor = "text-red-500";

      return (
            <>
                  <div className="flex gap-4 px-8 py-12 font-semibold">
                        <p>Total: </p>
                        <p className={balanceColor}>
                              R$ {balanceFormated}
                        </p>
                  </div>
            </>
      );
}