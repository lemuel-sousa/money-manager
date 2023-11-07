import { AuthContextProvider } from "@/context/auth-context";
import { cn } from "@/lib/utils";
import type { Metadata } from "next";
import { Poppins } from "next/font/google";
import "./globals.css";

const poppins = Poppins({ weight: "300", subsets: ["latin"] });

export const metadata: Metadata = {
	title: "Money Manager",
	description: "A financial manager that simplifie your cash control.",
};

export default function RootLayout({
	children,
}: {
	children: React.ReactNode;
}) {
	return (
		<AuthContextProvider>
			<html lang="en">
				<body className={cn(poppins.className, "bg-gray-100")}>{children}</body>
			</html>
		</AuthContextProvider>
	);
}
