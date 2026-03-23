import React, { useState } from 'react';
import { categoryApi } from '../api';
import type { Category } from '../types';
import './Categories.css';

const Categories: React.FC = () => {
  const [categories, setCategories] = useState<Category[]>([
    { categoryCode: 'CAT001', categoryName: 'Electronics' },
    { categoryCode: 'CAT002', categoryName: 'Furniture' },
  ]);
  const [error, setError] = useState('');
  const [showModal, setShowModal] = useState(false);
  const [editingCategory, setEditingCategory] = useState<Category | null>(null);

  const [formData, setFormData] = useState({
    categoryName: '',
    categoryCode: '',
  });

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError('');

    try {
      if (editingCategory) {
        await categoryApi.update(editingCategory.categoryCode, formData);
      } else {
        await categoryApi.create(formData);
      }
      // Update local state
      if (editingCategory) {
        setCategories(categories.map(c => 
          c.categoryCode === editingCategory.categoryCode ? formData : c
        ));
      } else {
        setCategories([...categories, formData]);
      }
      setShowModal(false);
      resetForm();
    } catch (err: any) {
      setError(err.response?.data?.message || 'Failed to save category');
    }
  };

  const openEditModal = (category: Category) => {
    setEditingCategory(category);
    setFormData({
      categoryName: category.categoryName,
      categoryCode: category.categoryCode,
    });
    setShowModal(true);
  };

  const resetForm = () => {
    setFormData({ categoryName: '', categoryCode: '' });
    setEditingCategory(null);
  };

  return (
    <div className="categories-page">
      <div className="page-header">
        <h1>Categories</h1>
        <button className="btn-primary" onClick={() => { resetForm(); setShowModal(true); }}>
          Add Category
        </button>
      </div>

      {error && <div className="error-banner">{error}</div>}

      <div className="table-container">
        <table className="data-table">
          <thead>
            <tr>
              <th>Category Code</th>
              <th>Category Name</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {categories.length === 0 ? (
              <tr>
                <td colSpan={3} className="empty-state">No categories found</td>
              </tr>
            ) : (
              categories.map((category) => (
                <tr key={category.categoryCode}>
                  <td>{category.categoryCode}</td>
                  <td>{category.categoryName}</td>
                  <td>
                    <button className="btn-edit" onClick={() => openEditModal(category)}>Edit</button>
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
            <h2>{editingCategory ? 'Edit Category' : 'Add Category'}</h2>
            <form onSubmit={handleSubmit}>
              <div className="form-group">
                <label>Category Code *</label>
                <input
                  type="text"
                  value={formData.categoryCode}
                  onChange={(e) => setFormData({ ...formData, categoryCode: e.target.value })}
                  placeholder="e.g., CAT001"
                  required
                  disabled={!!editingCategory}
                />
              </div>

              <div className="form-group">
                <label>Category Name *</label>
                <input
                  type="text"
                  value={formData.categoryName}
                  onChange={(e) => setFormData({ ...formData, categoryName: e.target.value })}
                  placeholder="e.g., Electronics"
                  required
                />
              </div>

              <div className="modal-actions">
                <button type="button" className="btn-secondary" onClick={() => setShowModal(false)}>
                  Cancel
                </button>
                <button type="submit" className="btn-primary">
                  {editingCategory ? 'Update' : 'Create'}
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
};

export default Categories;