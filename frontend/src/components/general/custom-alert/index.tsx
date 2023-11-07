import { Alert, AlertDescription, AlertTitle } from "@/components/ui/alert";
import { cn } from "@/lib/utils";
import { AlertTriangle, CheckIcon } from "lucide-react";

export const enum CustomAlertType {
      SUCCESS = "success",
      ERROR = "error" 
}

export type CustomAlertProps = {
      type: CustomAlertType.SUCCESS | CustomAlertType.ERROR
      title: string;
      message: string;
      className?: string;
}

export function CustomAlert({ type, title, message, className=""}: CustomAlertProps) {

      const alert = type === CustomAlertType.SUCCESS 
            ? <SuccessAlert title={title} message={message} className={className}/>
            : <FailureAlert title={title} message={message} className={className}/>;

      return alert;
}

type SuccessAlertProps = {
      title: string;
      message: string;
      className: string;
};

function SuccessAlert({ title, message, className }: SuccessAlertProps) {
      return (
            <Alert className={cn("border-emerald-500", className)}>
                  <CheckIcon className="h-8 w-8 stroke-emerald-500"/>
                  <AlertTitle className="font-bold mx-4">
                        {title}
                  </AlertTitle>
                  <AlertDescription className="font-thin mx-4">
                        {message}
                  </AlertDescription>
            </Alert>
      );
}

type FailureAlertProps = {
      title: string;
      message: string;
      className: string;
};

function FailureAlert({ title, message, className }: FailureAlertProps) {
      return (
            <Alert className={cn("border-red-500", className)}>
                  <AlertTriangle className="h-8 w-8 stroke-red-500" />
                  <AlertTitle className="font-bold mx-4" >
                        {title}
                  </AlertTitle>
                  <AlertDescription className="font-thin mx-4">
                        {message}
                  </AlertDescription>
            </Alert>
      );
}