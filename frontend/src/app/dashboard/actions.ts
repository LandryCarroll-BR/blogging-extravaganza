"use server";

import {auth} from "@/auth";
import {revalidatePath} from "next/cache";
import {redirect} from "next/navigation";

export async function createPostAction(formData: FormData) {
    const session = await auth();

    if (!session?.idToken) {
        return
    }

    const title = formData.get("title")?.toString();
    const content = formData.get("content")?.toString();

    if (!title || !content) {
        return
    }

    const response = await fetch("http://localhost:8080/api/posts", {
        method: "POST",
        headers: {
            Authorization: `Bearer ${session.idToken}`,
            "Content-Type": "application/json",
        },
        body: JSON.stringify({title, content}),
    });

    revalidatePath('/dashboard')
}

export async function editPost(formData: FormData) {
    const session = await auth();

    const id = formData.get("id")?.toString();
    const title = formData.get("title")?.toString();
    const content = formData.get("content")?.toString();

    if (!session?.idToken || !id || !title || !content) {
        return
    }

    const res = await fetch(`http://localhost:8080/api/posts/${id}`, {
        method: "PUT",
        headers: {
            Authorization: `Bearer ${session.idToken}`,
            "Content-Type": "application/json",
        },
        body: JSON.stringify({title, content}),
    });

    redirect("/dashboard");
}

export async function deletePost(formData: FormData) {
    const session = await auth();
    const id = formData.get("id")?.toString();

    if (!session?.idToken || !id) {
        return
    }

    await fetch(`http://localhost:8080/api/posts/${id}`, {
        method: "DELETE",
        headers: {
            Authorization: `Bearer ${session.idToken}`,
        },
    });

    redirect("/dashboard");
}