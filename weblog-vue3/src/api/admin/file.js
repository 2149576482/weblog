import axios from "@/axios";

export function uploadFile(form) {
    return axios.post("/admin/file/upload", form)
} 