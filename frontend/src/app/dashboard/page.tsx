import {auth} from "@/auth";
import {createPostAction, deletePost} from "./actions";
import Image from "next/image";
import {Card, CardContent, CardHeader, CardTitle} from "@/components/ui/card";
import {Input} from "@/components/ui/input";
import {Textarea} from "@/components/ui/textarea";
import {Button} from "@/components/ui/button";
import Link from "next/link";

export default async function DashboardPage() {
    const session = await auth();

    if (!session?.user || !session.idToken) {
        return <p>Unauthorized</p>;
    }

    const res = await fetch("http://localhost:8080/api/user/profile", {
        headers: {
            Authorization: `Bearer ${session.idToken}`,
        },
    });

    const profile = await res.json();

    const postRes = await fetch("http://localhost:8080/api/posts/mine", {
        headers: {
            Authorization: `Bearer ${session.idToken}`,
        },
        cache: "no-store",
    });

    const posts = await postRes.json();

    return (
        <main className="max-w-2xl mx-auto py-10 space-y-8">
            <div>
                <Button variant="link" asChild className="px-0">
                    <Link href="/" className="flex items-center gap-2">
                        Back to Feed
                    </Link>
                </Button>
            </div>

            {/* Profile Card */}
            <Card>
                <CardHeader>
                    <CardTitle>Welcome, {profile.name} 👋</CardTitle>
                </CardHeader>
                <CardContent className="flex items-center gap-4">
                    <Image
                        src={profile.picture}
                        alt="Profile Picture"
                        width={80}
                        height={80}
                        className="rounded-full"
                    />
                    <div>
                        <p className="text-lg font-medium">{profile.name}</p>
                        <p className="text-muted-foreground">{profile.email}</p>
                    </div>
                </CardContent>
            </Card>

            {/* Create Post Form */}
            <Card>
                <CardHeader>
                    <CardTitle>Create a New Post</CardTitle>
                </CardHeader>
                <CardContent>
                    <form action={createPostAction} className="space-y-4">
                        <Input name="title" placeholder="Post title" required/>
                        <Textarea name="content" placeholder="Write your post..." required/>
                        <Button type="submit">Publish</Button>
                    </form>
                </CardContent>
            </Card>


            {/* Posts List */}
            <div className="space-y-4">
                <h2 className="text-xl font-semibold">Your Posts</h2>

                {posts.length === 0 ? (
                    <p className="text-muted-foreground">You haven’t posted anything yet.</p>
                ) : (
                    posts.map((post: any) => (

                        <Card
                            key={post.id}
                            className="hover:shadow-lg transition"
                        >
                            <CardHeader className="flex flex-row justify-between items-start">
                                <div>
                                    <CardTitle className="text-base">{post.title}</CardTitle>
                                    <p className="text-sm text-muted-foreground line-clamp-2">
                                        {post.content}
                                    </p>
                                </div>
                                <div className="flex gap-2">
                                    <Button
                                        type="button"
                                        variant="outline"
                                        size="sm"
                                        asChild
                                    >
                                        <Link key={post.id} href={`/dashboard/posts/${post.id}`}>
                                            View
                                        </Link>
                                    </Button>
                                    <Button
                                        type="button"
                                        variant="outline"
                                        size="sm"
                                        asChild
                                    >
                                        <Link key={post.id} href={`/dashboard/posts/${post.id}/edit`}>
                                            Edit
                                        </Link>
                                    </Button>
                                    <form action={deletePost}>
                                        <input type="hidden" name="id" value={post.id}/>
                                        <Button variant="destructive" size="sm" type="submit">
                                            Delete
                                        </Button>
                                    </form>
                                </div>
                            </CardHeader>
                        </Card>

                    ))
                )}
            </div>

        </main>
    );
}
