'use client';

import {signOut, useSession} from 'next-auth/react';
import Image from 'next/image';
import {Button} from '@/components/ui/button';
import Link from 'next/link';

export function Navbar() {
    const {data: session} = useSession();

    return (
        <nav className="w-full flex items-center justify-between px-6 py-4 border-b">
            <div className="text-lg font-semibold">Blogging Extravaganza</div>

            {session?.user && (
                <div className="flex items-center gap-4">
                    <Button asChild variant="default">
                        <Link href="/dashboard">Create Post</Link>
                    </Button>

                    {session.user.image && (
                        <Image
                            src={session.user.image}
                            alt={session.user.name ?? 'Avatar'}
                            width={36}
                            height={36}
                            className="rounded-full"
                        />
                    )}
                    <span className="text-sm">{session.user.name}</span>

                    <Button variant="outline" onClick={() => signOut()}>
                        Log out
                    </Button>
                </div>
            )}
        </nav>
    );
}
