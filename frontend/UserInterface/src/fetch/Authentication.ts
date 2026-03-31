

export const login = async (formData: { email: string, password: string }) => {
    const response = await fetch("http://localhost:8100/karyawarna/login", {
        method: "POST",
        headers: {
            "Content-type": "application/json"
        },
        credentials: "include",
        body: JSON.stringify(formData)
    });

    if (response.ok) {
        const data = await profile();

        if (data) {
            window.location.href = "/dashboard";
        }

    }

    if (!response.ok) {
        return null
    }

    return response

}


export const profile = async () => {
    const response = await fetch("http://localhost:8100/karyawarna/profile", {
        method: "GET",
        credentials: "include"
    })
    return response

}