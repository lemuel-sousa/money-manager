import { backendApi } from "@/lib/api/axios";
import { AxiosError } from "axios";
import { NextRequest } from "next/server";
import { authToken } from "../auth/token/authentication";
import { BackendResponseErrorType } from "../error/types";



type InsertActivityRequestType = {
      description: string;
      date: Date;
      value: number;
      type: "expense" | "revenue";
}

export async function POST(request: NextRequest) {

      const token = authToken(request);

      try {
            const data = await request.json() as InsertActivityRequestType;

            const jsonData = JSON.stringify(data);

            const result = await backendApi.post("/activities", jsonData, {
                  headers: {
                        "Authorization": `Bearer ${token}`
                  }
            });

            return new Response("", { status: 201 });

      } catch (e) {
            const axiosError = e as AxiosError;

            const { status, error } = axiosError.response?.data as BackendResponseErrorType;

            if (status)
                  return new Response(JSON.stringify(new AxiosError(error, status.toString())), { status });
            else
                  return new Response(JSON.stringify(new AxiosError(axiosError.message, axiosError.code)),
                        { status: axiosError.status || 500 });
      }


}

export type ActivityType = {
      id: string;
      description: string;
      date: Date;
      value: number;
      type: "revenue" | "expense";
}

export type ActivitiesType = {
      activities: ActivityType[];
}

export async function GET(request: NextRequest) {
      const token = authToken(request);
      
      try {
            const result = await backendApi.get("/activities", {
                  headers: {
                        "Authorization": `Bearer ${token}`}
            });
             
            const activities = result.data as ActivitiesType;

            return new Response(JSON.stringify(activities), { status: 200});
            
      } catch (e) {
            const axiosError = e as AxiosError;

            const { status, error } = axiosError.response?.data as BackendResponseErrorType;

            if (status) {
                  return new Response(JSON.stringify(new AxiosError(error, status.toString())), { status });
            } else {
                  return new Response(JSON.stringify( new AxiosError(axiosError.message, axiosError.code)),
                        { status: axiosError.status || 500 });
            }
      }
      
}