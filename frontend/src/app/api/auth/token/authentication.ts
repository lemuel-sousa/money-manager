import { NextRequest } from "next/server";

export function authToken(request: NextRequest) {
      const authToken = request.cookies.get("money-manager.token")?.value;

      if (!authToken)
            return new Response(JSON.stringify(new Error("Usuário não autorizado")), { status: 401 });

      return authToken;
}