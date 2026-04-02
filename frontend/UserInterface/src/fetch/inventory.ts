
export const addProduct = async (formData: { productName: string, purchasedPrice: number, sellPrice: number, description: string, brand: string, category: string, supplier: string, stock: number }) => {
    const response = await fetch("http://localhost:8101/karyawarna/admin/create/product", {
        method: "POST",
        credentials: "include",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(formData)
    });
    if (response.ok) {
        const result = await response.json();
        return result;
    }

    if (!response.ok) {
        const error = await response.json();
        throw new Error(error.message);
    }
}

export const getSupplier = async () => {
    const response = await fetch("http://localhost:8101/karyawarna/admin/suppliers", {
        method: "GET",

    })
}