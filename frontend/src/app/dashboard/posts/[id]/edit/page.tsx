import {auth} from "@/auth";
import {Input} from "@/components/ui/input";
import {Textarea} from "@/components/ui/textarea";
import {Button} from "@/components/ui/button";
import {editPost} from "@/app/dashboard/actions";

export default async function EditPostPage({params}: { params: { id: string } }) {
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
        <main className="max-w-2xl mx-auto py-10">
            <h1 className="text-2xl font-bold mb-6">Edit Post</h1>
            <form action={editPost} className="space-y-4">
                <input type="hidden" name="id" value={params.id}/>
                <Input name="title" defaultValue={post.title} required/>
                <Textarea name="content" defaultValue={post.content} required/>
                <Button type="submit">Update</Button>
            </form>
        </main>
    );
}
