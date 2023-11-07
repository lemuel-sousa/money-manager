import { Button } from "@/components/ui/button";
import { ActivityTableContext } from "@/context/activity-table-context";
import { frontendApi } from "@/lib/api/axios";
import { useContext } from "react";

type RemoveButtonProps = {
      id: string;
}

async function removeActivity(id: string) {
      try {
            const url = `/activities/${id}`;
            const result = await frontendApi.delete(url);
      } catch (e) {
            alert("Não é possível remover a tarefa no momento!");
      }
}

export default function RemoveButton({ id }: RemoveButtonProps) {
      const activityTableContext = useContext(ActivityTableContext);

      return (
            <Button
                  variant="ghost"
                  key={id}
                  onClick={async () => {
                        await removeActivity(id)
                        activityTableContext.refreshTable();
                  }}
            > 
                  Remover
            </Button>

      );
}