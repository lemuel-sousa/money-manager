import { backendApi } from "@/lib/api/axios";
import { AxiosError } from "axios";
import { NextRequest } from "next/server";
import { authToken } from "../../auth/token/authentication";
import { BackendResponseErrorType } from "../../error/types";

type DeleteActivityType = {
      params: {
            id: string;
      }
};

export async function DELETE(request: NextRequest, { params }: DeleteActivityType) {
      const token = authToken(request);

      try {
            const url = `/activities/${params.id}`;
            const result = await backendApi.delete(url, {
                  headers: {
                        Authorization: `Bearer ${token}`
                  }
            });

            return new Response("", { status: 200});
      } catch (e) {
            const axiosError = e as AxiosError;

            const { status, error } = axiosError.response?.data as BackendResponseErrorType;

            if (status)
                  return new Response(JSON.stringify(new AxiosError(error, status.toString())), { status });
            else
                  return new Response(JSON.stringify(new AxiosError(axiosError.message, axiosError.code)),
                        { status: axiosError.status || 500 })
      }


}