import React, { useState } from 'react';
import { brandApi } from '../api';
import type { Brand } from '../types';
import './Brands.css';

const Brands: React.FC = () => {
  const [brands, setBrands] = useState<Brand[]>([]);
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');
  const [showModal, setShowModal] = useState(false);

  const [formData, setFormData] = useState({
    brandName: '',
    brandCode: '',
    supplierName: '',
  });

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError('');
    setSuccess('');

    try {
      const response = await brandApi.create(formData);
      setBrands([...brands, response.data.data]);
      setSuccess('Brand created successfully!');
      setShowModal(false);
      resetForm();
    } catch (err: any) {
      setError(err.response?.data?.message || 'Failed to create brand');
    }
  };

  const resetForm = () => {
    setFormData({ brandName: '', brandCode: '', supplierName: '' });
  };

  return (
    <div className="brands-page">
      <div className="page-header">
        <h1>Brands</h1>
        <button className="btn-primary" onClick={() => setShowModal(true)}>
          Add Brand
        </button>
      </div>

      {error && <div className="error-banner">{error}</div>}
      {success && <div className="success-banner">{success}</div>}

      <div className="table-container">
        <table className="data-table">
          <thead>
            <tr>
              <th>Brand Code</th>
              <th>Brand Name</th>
              <th>Supplier</th>
            </tr>
          </thead>
          <tbody>
            {brands.length === 0 ? (
              <tr>
                <td colSpan={3} className="empty-state">No brands found. Click "Add Brand" to create one.</td>
              </tr>
            ) : (
              brands.map((brand) => (
                <tr key={brand.id}>
                  <td>{brand.brandCode}</td>
                  <td>{brand.brandName}</td>
                  <td>{brand.supplierName}</td>
                </tr>
              ))
            )}
          </tbody>
        </table>
      </div>

      {showModal && (
        <div className="modal-overlay" onClick={() => setShowModal(false)}>
          <div className="modal" onClick={(e) => e.stopPropagation()}>
            <h2>Add Brand</h2>
            <form onSubmit={handleSubmit}>
              <div className="form-group">
                <label>Brand Code *</label>
                <input
                  type="text"
                  value={formData.brandCode}
                  onChange={(e) => setFormData({ ...formData, brandCode: e.target.value })}
                  placeholder="e.g., BRAND001"
                  required
                />
              </div>

              <div className="form-group">
                <label>Brand Name *</label>
                <input
                  type="text"
                  value={formData.brandName}
                  onChange={(e) => setFormData({ ...formData, brandName: e.target.value })}
                  placeholder="e.g., Samsung"
                  required
                />
              </div>

              <div className="form-group">
                <label>Supplier Name *</label>
                <input
                  type="text"
                  value={formData.supplierName}
                  onChange={(e) => setFormData({ ...formData, supplierName: e.target.value })}
                  placeholder="e.g., PT Supplier Indonesia"
                  required
                />
              </div>

              <div className="modal-actions">
                <button type="button" className="btn-secondary" onClick={() => setShowModal(false)}>
                  Cancel
                </button>
                <button type="submit" className="btn-primary">
                  Create
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
};

export default Brands;