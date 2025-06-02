// src/context/AuthContext.tsx

import React, { createContext, useContext, useState } from 'react';

interface AuthContextType {
  isAuthenticated: boolean;
  login: () => void;
  logout: () => void;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const AuthProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
  const [isAuthenticated, setIsAuthenticated] = useState<boolean>(() => {
    return sessionStorage.getItem('authenticated') === 'true'; // ✅ changed
  });

  const login = () => {
    sessionStorage.setItem('authenticated', 'true'); // ✅ changed
    setIsAuthenticated(true);
  };

  const logout = () => {
    sessionStorage.removeItem('authenticated'); // ✅ changed
    setIsAuthenticated(false);
  };

  return (
      <AuthContext.Provider value={{ isAuthenticated, login, logout }}>
        {children}
      </AuthContext.Provider>
  );
};

export const useAuth = (): AuthContextType => {
  const context = useContext(AuthContext);
  if (!context) throw new Error('useAuth must be used within AuthProvider');
  return context;
};
