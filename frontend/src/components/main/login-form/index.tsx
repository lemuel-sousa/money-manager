'use client'
import { LoginResponseType } from "@/app/api/auth/login/route";
import { CustomAlert, CustomAlertType } from "@/components/general/custom-alert";
import { Button } from "@/components/ui/button";
import { Form, FormControl, FormField, FormItem, FormMessage } from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import { AuthContext } from "@/context/auth-context";
import { frontendApi } from "@/lib/api/axios";
import { zodResolver } from "@hookform/resolvers/zod";
import { AxiosError } from "axios";
import { BadgeDollarSign } from "lucide-react";
import { useRouter } from "next/navigation";
import { useContext, useState } from "react";
import { useForm } from "react-hook-form";
import { z } from "zod";

const loginFormSchema = z.object({
      username: z.string().min(3, { message: "Usuário inválido" }),
      password: z.string().min(6, { message: "Senha inválida" })
});

type LoginFormType = z.infer<typeof loginFormSchema>;
export default function LoginForm() {

      const [message, setMessage] = useState(<></>);

      const authContext = useContext(AuthContext);

      const router = useRouter();

      const loginForm = useForm<LoginFormType>({
            resolver: zodResolver(loginFormSchema),
            defaultValues: {
                  username: "",
                  password: ""
            }
      });

      async function handleLoginSubmit({ username, password }: LoginFormType) {

            const data = JSON.stringify({
                  username,
                  password
            });

            try {
                  const result = await frontendApi.post("/auth/login", data);

                  const { token, error } = result.data as LoginResponseType;

                  if (token) {
                        authContext.signIn(token);

                        const message = <CustomAlert
                              type={CustomAlertType.SUCCESS}
                              title="Login efetuado!"
                              message="Aguarde redirecionamento..."
                        />;
                        setMessage(message);

                        router.push("/dashboard");
                  }
                  else {
                        const message = <CustomAlert
                              type={CustomAlertType.ERROR}
                              title="Falha no login!"
                              message={error || "Erro desconhecido"}
                        />;

                        setMessage(message);
                  }
            } catch (e) {
                  const axiosError = e as AxiosError;

                  const message = <CustomAlert
                        type={CustomAlertType.ERROR}
                        title="Falha no login!"
                        message={axiosError.message}
                  />;

                  setMessage(message);
            }
      }

      return (
            <>
                  <div className="flex pb-56 items-center h-screen">

                        <div className="container space-y-3 p-8 max-w-md bg-gray-50 rounded-xl shadow-md">
                              <span className="flex items-center gap-2">
                                    <BadgeDollarSign className="text-slate-500" size={48} />
                                    <h1 className="uppercase text-slate-600 font-bold text-lg">
                                          Money Manager
                                    </h1>
                              </span>


                              <Form {...loginForm}>
                                    <form onSubmit={loginForm.handleSubmit(handleLoginSubmit)} className="space-y-2">
                                          {message}
                                          <FormField
                                                control={loginForm.control}
                                                name="username"
                                                render={({ field }) => {
                                                      return (
                                                            <FormItem>
                                                                  <FormControl>
                                                                        <Input type="text" placeholder="Insert your username" {...field} />
                                                                  </FormControl>
                                                                  <FormMessage />
                                                            </FormItem>
                                                      )
                                                }}
                                          />

                                          <FormField
                                                control={loginForm.control}
                                                name="password"
                                                render={({ field }) => {
                                                      return (<FormItem>
                                                            <FormControl>
                                                                  <Input type="password" placeholder="Insert your password" {...field} />
                                                            </FormControl>
                                                            <FormMessage />
                                                      </FormItem>)
                                                }}
                                          />
                                          <Button type="submit">Send</Button>
                                    </form>
                              </Form>
                        </div>
                  </div>
            </>
      );
}