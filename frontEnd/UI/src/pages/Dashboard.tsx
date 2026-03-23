import React, { useEffect, useState } from 'react';
import { productApi } from '../api';
import './Dashboard.css';

interface Stats {
  totalProducts: number;
  lowStock: number;
  totalValue: number;
}

const Dashboard: React.FC = () => {
  const [stats, setStats] = useState<Stats>({
    totalProducts: 0,
    lowStock: 0,
    totalValue: 0,
  });
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchStats = async () => {
      try {
        const response = await productApi.search({ page: 0, size: 1000 });
        const products = response.data.content;
        
        const totalProducts = products.length;
        const lowStock = products.filter((p: any) => p.stock < 10).length;
        const totalValue = products.reduce((sum: number, p: any) => sum + (p.sellPrice * p.stock), 0);
        
        setStats({ totalProducts, lowStock, totalValue });
      } catch (err: any) {
        setError('Failed to load dashboard data. Make sure backend is running.');
        console.error(err);
      } finally {
        setLoading(false);
      }
    };

    fetchStats();
  }, []);

  if (loading) {
    return <div className="loading">Loading dashboard...</div>;
  }

  return (
    <div className="dashboard">
      <h1>Dashboard</h1>
      
      {error && <div className="error-banner">{error}</div>}
      
      <div className="stats-grid">
        <div className="stat-card">
          <div className="stat-icon">📦</div>
          <div className="stat-content">
            <h3>Total Products</h3>
            <p className="stat-value">{stats.totalProducts}</p>
          </div>
        </div>
        
        <div className="stat-card warning">
          <div className="stat-icon">⚠️</div>
          <div className="stat-content">
            <h3>Low Stock Items</h3>
            <p className="stat-value">{stats.lowStock}</p>
          </div>
        </div>
        
        <div className="stat-card success">
          <div className="stat-icon">💰</div>
          <div className="stat-content">
            <h3>Total Inventory Value</h3>
            <p className="stat-value">Rp {stats.totalValue.toLocaleString('id-ID')}</p>
          </div>
        </div>
      </div>

      <div className="dashboard-info">
        <div className="info-card">
          <h3>System Status</h3>
          <div className="status-indicator online">
            <span className="status-dot"></span>
            Backend Connected
          </div>
          <p className="info-text">
            Your backend services are running. Use the sidebar to navigate to Products, Categories, Brands, or Suppliers.
          </p>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;