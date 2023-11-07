'use client'

import { CustomAlert, CustomAlertType } from "@/components/general/custom-alert";
import { DatePicker } from "@/components/general/date-picker";
import { Button } from "@/components/ui/button";
import { Form, FormControl, FormField, FormItem, FormMessage } from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { ActivityTableContext } from "@/context/activity-table-context";
import { frontendApi } from "@/lib/api/axios";
import { zodResolver } from "@hookform/resolvers/zod";
import { AxiosError } from "axios";
import { useContext, useState } from "react";
import { useForm } from "react-hook-form";
import { z } from "zod";

enum ActivityType {
      REVENUE = "revenue",
      EXPENSE = "expense"
}

const insertFormSchema = z.object({
      description: z.string({ required_error: "Insira uma descrição." })
            .min(3, { message: "Pelo menos três caracteres." }),

      date: z.date({ required_error: "Insira uma data." }),

      value: z.coerce.number({ required_error: "Insira um valor." })
            .min(0.01, { message: "O valor precisa ser maior que zero." }),

      type: z.nativeEnum(ActivityType, { required_error: "Selecione um tipo." })
});

type InsertFormType = z.infer<typeof insertFormSchema>;

export default function InsertActivityForm() {

      const [insertMessagem, setInsertMessage] = useState<JSX.Element>(<></>);

      const activityTableContext = useContext(ActivityTableContext);

      const insertForm = useForm<InsertFormType>({
            resolver: zodResolver(insertFormSchema),
            defaultValues: {
                  description: "",
                  date: new Date(),
                  // @ts-expect-error
                  value: "",
                  type: ActivityType.REVENUE
            }
      });

      async function onInsertFormSubmit({ date, description, value, type }: InsertFormType) {
            console.log(date, description, value, type);

            const formatedData = JSON.stringify({
                  date: date.toISOString(),
                  description,
                  value,
                  type
            })

            try {

                  const result = await frontendApi.post("/activities", formatedData);

                  const message = <CustomAlert
                        className="w-fit float-right mb-4"
                        title="Registrado com sucesso!"
                        message={`${type} inserida com sucesso!`}
                        type={CustomAlertType.SUCCESS}
                  />
                  setInsertMessage(message);

                  activityTableContext.refreshTable();

            } catch (e) {
                  const axiosError = e as AxiosError;

                  const data = axiosError.response?.data as { message: string, code: number };

                  var errorMessage;

                  if (data) {
                        errorMessage = data.message;
                  } else {
                        errorMessage = axiosError.message;
                  }

                  const message = <CustomAlert
                        className="w-fit float-right mb-4"
                        message={`${errorMessage}`}
                        title="Erro ao registrar atividade."
                        type={CustomAlertType.ERROR}
                  />

                  setInsertMessage(message);
            }

            setTimeout(() => setInsertMessage(<></>), 2500); // each 2,5 sec

      }

      return (
            <div>
                  <Form {...insertForm}>
                        <form onSubmit={insertForm.handleSubmit(onInsertFormSubmit)}
                              className="flex justify-center container space-x-2 p-8 pb-4"
                        >
                              <FormField
                                    control={insertForm.control}
                                    name="date"
                                    render={({ field }) => {
                                          return (
                                                <FormItem className="w-max">
                                                      <DatePicker date={field.value} setDate={field.onChange} />
                                                      <FormMessage />
                                                </FormItem>
                                          )
                                    }}
                              />

                              <FormField
                                    control={insertForm.control}
                                    name="description"
                                    render={({ field }) => {
                                          return (
                                                <FormItem className="w-full">
                                                      <FormControl>
                                                            <Input type="text" placeholder="Descrição de Atividade" {...field}/>
                                                      </FormControl>
                                                      <FormMessage />
                                                </FormItem>
                                          )
                                    }}
                              />

                              <FormField
                                    control={insertForm.control}
                                    name="value"
                                    render={({ field }) => {
                                          return (
                                                <FormItem className="w-max">
                                                      <FormControl>
                                                            <Input type="number" className="w-max" placeholder="Insirara o valor" {...field}/>
                                                      </FormControl>
                                                      <FormMessage />
                                                </FormItem>
                                          )
                                    }}
                              />

                              <FormField
                                    control={insertForm.control}
                                    name="type"
                                    render={({ field }) => {
                                          return (
                                                <FormItem className="w-max">
                                                      <Select onValueChange={field.onChange} defaultValue={field.value}>
                                                            <FormControl>
                                                                  <SelectTrigger className="w-max">
                                                                        <SelectValue placeholder="Selecione o tipo" />
                                                                  </SelectTrigger>
                                                            </FormControl>
                                                            <SelectContent>
                                                                  <SelectItem value={ActivityType.REVENUE}>Entrada</SelectItem>
                                                                  <SelectItem value={ActivityType.EXPENSE}>Saída</SelectItem>
                                                            </SelectContent>
                                                      </Select>
                                                      <FormMessage />
                                                </FormItem>
                                          )
                                    }}
                              />

                              <Button type="submit">Incluir</Button>
                        </form>
                        {insertMessagem}
                  </Form>
            </div>
      );
}