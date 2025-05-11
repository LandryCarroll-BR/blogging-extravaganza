import {auth} from "@/auth";

export default async function PostDetailPage({params}: { params: { id: string } }) {
    const session = await auth();

    const res = await fetch(`http://localhost:8080/api/posts/${params.id}`, {
        headers: {
            Authorization: `Bearer ${session?.idToken}`,
        },
        cache: "no-store",
    });

    if (!res.ok) return <div>Post not found.</div>;

    const post = await res.json();

    return (
        <main className="max-w-2xl mx-auto py-10 space-y-4">
            <h1 className="text-2xl font-bold">{post.title}</h1>
            <p className="text-sm text-muted-foreground">
                {new Date(post.createdAt).toLocaleString()}
            </p>
            <div className="mt-4 text-base whitespace-pre-line">{post.content}</div>
        </main>
    );
}
