# Plan: Brand-Category-Product Relationship Architecture

## TL;DR
We will introduce a dedicated Brand-Category join (brand_category) to model that brands operate across multiple categories while preserving the existing Product-Brand (product_brand) mapping. Product remains linked to a single ProductCategory (as currently), enabling cross-category brand support without changing core product taxonomy.

## Context
 - Current mappings:
   - Product <-> Brand: Many-to-many (Product is the owning side with join table product_brand)
   - Product -> ProductCategory: Many-to-one (each Product has a single category)
- Business requirement: A Brand can operate in multiple categories; a Category can host multiple brands.
- Rationale: A separate Brand-Category bridge avoids forcing ProductCategory to be a dependency of Brand or Product and supports flexible reporting.

## Work Objectives
- Introduce brand_category join table and mapping between Brand and ProductCategory.
- Keep existing Product-Brand mapping intact; ensure no breaking changes to existing queries.
- Extend domain model with minimal changes to support new queries:
  - Which brands operate in a given category?
  - Which categories does a brand operate in?
- Provide migration script and seed data guidance.
- Add tests for the bridge behavior and basic queries.
- Document migration steps and backward compatibility notes.

## Definition of Done (DoD)
- Brand-Category bridge mapped as a ManyToMany on Brand and inverse on ProductCategory.
- DAO/repository queries exist for common lookups (brand by category, category by brand).
- DB schema updated with brand_category table.
- Tests cover bridging queries.
- Data migration guidance provided.
- No regressions on existing Product-Brand relations.

## Key Decisions
- Decision: Use a dedicated join table brand_category for Brand-Category mapping. Rationale: simplicity, explicitness, and clear reporting boundaries without overhauling Product-Category semantics.
- Decision: Do not require Products to belong to multiple categories (keep existing ProductCategory as a single-valued relation for now) to minimize risk. If later needed, we can switch Product to ManyToMany with Category.

## Scope Boundaries
- IN: Brand-Category bridge; Product-Brand unchanged; Product-Category unchanged.
- OUT: Changing Product Category from ManyToOne to ManyToMany; altering existing Product->Category semantics.

## TODOs
- [ ] Create brand_category join table with foreign keys (brand_id -> Brand, category_id -> ProductCategory)
- [ ] Update Brand.java:
  - Add: @ManyToMany
  - @JoinTable(name = "brand_category", joinColumns = @JoinColumn(name = "brand_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
  - field: List<ProductCategory> categoryList = new ArrayList<>();
- [ ] Update ProductCategory.java:
  - Add: @ManyToMany(mappedBy = "categoryList")
  - field: List<Brand> brandList = new ArrayList<>();
- [ ] Optionally update Brand and ProductCategory toString/JSON representation to avoid lazy loading pitfalls.
- [ ] Create DB migration script for brand_category table.
- [ ] Create integration tests for bridge queries:
  - fetch brands for a category
  - fetch categories for a brand
- [ ] Seed sample data if needed for QA
- [ ] Dokument: Migration notes and rollback plan

## Verification Strategy
- Query design:
  - BrandRepository.findBrandsByCategoryId(categoryId)
  - ProductCategoryRepository.findCategoriesByBrandId(brandId)
- Tests:
  - Happy path for brand-category relation
  - Negative tests for non-existent brand/category
- Ensure that existing product-brand flows remain unaffected

## Execution Strategy
- Wave 1: Model and schema
  - Implement Brand.categoryList mapping; ProductCategory.brandList mapping
  - Rename/adjust imports; compile-time corrections
- Wave 2: Persistence and migrations
  - Create and apply brand_category schema
  - Run integration tests
- Wave 3: Data seeding and QA
  - Seed relationships for a few brands/categories
  - Manual QA checks
- Wave 4: Documentation and Handover
  - Update architecture docs; prepare rollback plan

## References
- JPA ManyToMany best practices
- Domain-driven design notes on bridging relationships
- Current data model: Product.java, Brand.java, ProductCategory.java

## Acceptance Criteria (Agent-Executable)
- Brand-Category join exists and is queryable
- No breaking changes to Product-Brand functionality
- Tests cover Brand-Category lookups
- Data migration script provided
