import { backendApi } from "@/lib/api/axios";
import { AxiosError } from "axios";
import { NextRequest } from "next/server";
import { authToken } from "../../auth/token/authentication";
import { BackendResponseErrorType } from "../../error/types";

type ActivityBalanceBackendResponseType = {
      balance: number;
}

export async function GET(request: NextRequest) {
      const token = authToken(request);

      try {
            const result = await backendApi("/activities/balance", {
                  headers: {
                        "Authorization": `Bearer ${token}`
                  }
            });

            const balance = result.data as ActivityBalanceBackendResponseType;

            return new Response(JSON.stringify(balance), { status: 200 });

      } catch (e) {
            const axiosError = e as AxiosError;

            const { status, error } = axiosError.response?.data as BackendResponseErrorType;

            if (status)
                  return new Response(JSON.stringify(new AxiosError(error, status.toString())),
                        { status });

            else
                  return new Response(JSON.stringify(new AxiosError(axiosError.message, axiosError.code)),
                        { status: axiosError.status || 500 });
      }
}