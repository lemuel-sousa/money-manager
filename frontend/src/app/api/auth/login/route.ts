import { backendApi } from "@/lib/api/axios";
import { AxiosError } from "axios";
import { NextRequest } from "next/server";

export type LoginResponseType = {
      token?: string;
      error?: string;
};

type BackendLoginResponseType = {
      token: string;
}

type BackendLoginErrorResponseType = {
      error: string;
      path: string;
      status: number;
      timestamp: string;
}

export async function POST(request: NextRequest) {

      const { username, password } = await request.json();

      const data = JSON.stringify({ username, password })

      var response: LoginResponseType;

      try {
            const result = await backendApi.post("/auth/login", data);
            const { token } = result.data;
            response = { token };
      } catch (e) {
            const axiosError = e as AxiosError;

            const { status, error } = axiosError.response?.data as BackendLoginErrorResponseType;

            if (status) response = { error };
            else response = { error: axiosError.message };

      }

      return new Response(JSON.stringify(response));

}