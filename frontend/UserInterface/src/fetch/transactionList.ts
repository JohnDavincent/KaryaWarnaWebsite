export const getTransaction = async () => {
    const response = await fetch("http://localhost:8102/karyawarna/latest-order", {
        method: "GET",
        credentials: "include"
    })
    const result = await response.json()
    console.log(result)
    return result;

}