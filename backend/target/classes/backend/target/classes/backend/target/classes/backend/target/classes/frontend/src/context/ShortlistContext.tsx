import { createContext, useContext, useState, ReactNode } from "react";

const ShortlistContext = createContext<any>(null);

export function ShortlistProvider({ children }: { children: ReactNode }) {
    const [shortlist, setShortlist] = useState<any[]>([]);

    const addToShortlist = (variant: any) =>
        setShortlist((prev) => [...prev, variant]);

    return (
        <ShortlistContext.Provider value={{ shortlist, addToShortlist }}>
            {children}
        </ShortlistContext.Provider>
    );
}

export function useShortlist() {
    return useContext(ShortlistContext);
}
