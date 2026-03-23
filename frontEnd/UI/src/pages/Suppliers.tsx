import React, { useState, useEffect } from 'react';
import { supplierApi } from '../api';
import type { Supplier } from '../types';
import './Suppliers.css';

const Suppliers: React.FC = () => {
  const [suppliers, setSuppliers] = useState<Supplier[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [showModal, setShowModal] = useState(false);
  const [editingSupplier, setEditingSupplier] = useState<Supplier | null>(null);
  const [searchTerm, setSearchTerm] = useState('');

  const [formData, setFormData] = useState({
    supplierName: '',
    phoneNumber: '',
    contactPerson: '',
    desc: '',
  });

  useEffect(() => {
    // Mock data since no search endpoint exists
    setSuppliers([
      { id: '1', supplierName: 'PT Maju Jaya', phoneNumber: '081234567890', contactPerson: 'John Doe', desc: 'Electronics supplier' },
      { id: '2', supplierName: 'CV Sumber Rejeki', phoneNumber: '089876543210', contactPerson: 'Jane Smith', desc: 'Office supplies' },
    ]);
    setLoading(false);
  }, []);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError('');

    try {
      if (editingSupplier) {
        const response = await supplierApi.update(editingSupplier.id, formData);
        setSuppliers(suppliers.map(s => 
          s.id === editingSupplier.id ? response.data.data : s
        ));
      } else {
        const response = await supplierApi.create(formData);
        setSuppliers([...suppliers, response.data.data]);
      }
      setShowModal(false);
      resetForm();
    } catch (err: any) {
      setError(err.response?.data?.message || 'Failed to save supplier');
    }
  };

  const handleDelete = async (id: string) => {
    if (!confirm('Are you sure you want to delete this supplier?')) return;

    try {
      await supplierApi.delete(id);
      setSuppliers(suppliers.filter(s => s.id !== id));
    } catch (err: any) {
      setError(err.response?.data?.message || 'Failed to delete supplier');
    }
  };

  const openEditModal = (supplier: Supplier) => {
    setEditingSupplier(supplier);
    setFormData({
      supplierName: supplier.supplierName,
      phoneNumber: supplier.phoneNumber,
      contactPerson: supplier.contactPerson,
      desc: supplier.desc || '',
    });
    setShowModal(true);
  };

  const resetForm = () => {
    setFormData({ supplierName: '', phoneNumber: '', contactPerson: '', desc: '' });
    setEditingSupplier(null);
  };

  const filteredSuppliers = suppliers.filter(s =>
    s.supplierName.toLowerCase().includes(searchTerm.toLowerCase()) ||
    s.contactPerson.toLowerCase().includes(searchTerm.toLowerCase())
  );

  if (loading) {
    return <div className="loading">Loading suppliers...</div>;
  }

  return (
    <div className="suppliers-page">
      <div className="page-header">
        <h1>Suppliers</h1>
        <button className="btn-primary" onClick={() => { resetForm(); setShowModal(true); }}>
          Add Supplier
        </button>
      </div>

      {error && <div className="error-banner">{error}</div>}

      <div className="search-bar">
        <input
          type="text"
          placeholder="Search suppliers..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
      </div>

      <div className="table-container">
        <table className="data-table">
          <thead>
            <tr>
              <th>Supplier Name</th>
              <th>Contact Person</th>
              <th>Phone Number</th>
              <th>Description</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {filteredSuppliers.length === 0 ? (
              <tr>
                <td colSpan={5} className="empty-state">No suppliers found</td>
              </tr>
            ) : (
              filteredSuppliers.map((supplier) => (
                <tr key={supplier.id}>
                  <td>{supplier.supplierName}</td>
                  <td>{supplier.contactPerson}</td>
                  <td>{supplier.phoneNumber}</td>
                  <td>{supplier.desc || '-'}</td>
                  <td>
                    <button className="btn-edit" onClick={() => openEditModal(supplier)}>Edit</button>
                    <button className="btn-delete" onClick={() => handleDelete(supplier.id)}>Delete</button>
                  </td>
                </tr>
              ))
            )}
          </tbody>
        </table>
      </div>

      {showModal && (
        <div className="modal-overlay" onClick={() => setShowModal(false)}>
          <div className="modal" onClick={(e) => e.stopPropagation()}>
            <h2>{editingSupplier ? 'Edit Supplier' : 'Add Supplier'}</h2>
            <form onSubmit={handleSubmit}>
              <div className="form-group">
                <label>Supplier Name *</label>
                <input
                  type="text"
                  value={formData.supplierName}
                  onChange={(e) => setFormData({ ...formData, supplierName: e.target.value })}
                  placeholder="e.g., PT Maju Jaya"
                  required
                />
              </div>

              <div className="form-group">
                <label>Contact Person *</label>
                <input
                  type="text"
                  value={formData.contactPerson}
                  onChange={(e) => setFormData({ ...formData, contactPerson: e.target.value })}
                  placeholder="e.g., John Doe"
                  required
                />
              </div>

              <div className="form-group">
                <label>Phone Number * (12 digits)</label>
                <input
                  type="text"
                  value={formData.phoneNumber}
                  onChange={(e) => setFormData({ ...formData, phoneNumber: e.target.value })}
                  placeholder="e.g., 081234567890"
                  required
                  maxLength={12}
                />
              </div>

              <div className="form-group">
                <label>Description</label>
                <textarea
                  value={formData.desc}
                  onChange={(e) => setFormData({ ...formData, desc: e.target.value })}
                  placeholder="Optional description..."
                  rows={3}
                />
              </div>

              <div className="modal-actions">
                <button type="button" className="btn-secondary" onClick={() => setShowModal(false)}>
                  Cancel
                </button>
                <button type="submit" className="btn-primary">
                  {editingSupplier ? 'Update' : 'Create'}
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
};

export default Suppliers;