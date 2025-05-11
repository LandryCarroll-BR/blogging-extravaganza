import {auth} from "@/auth";
import {SignIn} from "@/components/sign-in";
import {Navbar} from "@/components/navbar";
import {Card, CardContent, CardHeader, CardTitle} from "@/components/ui/card";
import {Separator} from "@/components/ui/separator";

export default async function HomePage() {
    const session = await auth();

    if (!session?.user || !session.idToken) {
        return (
            <div className="flex flex-col items-center justify-center min-h-screen p-8">
                <h1 className="text-3xl font-semibold mb-6">Welcome to Blogging Extravaganza</h1>
                <SignIn/>
            </div>
        );
    }

    const res = await fetch("http://localhost:8080/api/posts", {
        headers: {
            Authorization: `Bearer ${session.idToken}`,
        },
        cache: "no-store",
    });

    const posts = await res.json();

    return (
        <>
            <Navbar/>
            <main className="max-w-2xl mx-auto py-12 px-4 space-y-6">
                <h1 className="text-2xl font-semibold">Latest Posts</h1>

                {posts.length === 0 ? (
                    <p className="text-muted-foreground">No posts yet.</p>
                ) : (
                    posts.map((post: any) => (
                        <Card key={post.id}>
                            <CardHeader>
                                <CardTitle>{post.title}</CardTitle>
                            </CardHeader>
                            <CardContent className="space-y-2">
                                <p className="text-sm text-muted-foreground">by {post.authorEmail}</p>
                                <Separator/>
                                <p>{post.content}</p>
                            </CardContent>
                        </Card>
                    ))
                )}
            </main>
        </>
    );
}
