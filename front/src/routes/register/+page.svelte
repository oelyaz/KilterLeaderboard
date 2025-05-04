<script lang="ts">
    import Navbar from "$lib/Navbar.svelte"
    import "$lib/global.css"
    import "./page.css"
    import {onMount} from "svelte";

    let username: string="";
    let friends: Set<string> = new Set();

    async function getFriends() {
        try {
            const res = await fetch("http://localhost:8080/friends/allString");
            if (!res.ok) throw new Error("Failed to fetch Friends!");
            friends = new Set<string>(await res.json());
        } catch (error) {
            console.error(error);
        }
        console.log(friends);
    }

    async function postName() {
        if (isFriend && username.length > 0) {
            try {
                const res = await fetch("http://localhost:8080/users/" + username,
                    {method:"POST",
                     headers: {
                         "Content-Type": "application/json",
                     },
                      //body: JSON.stringify({ username })
                    });
                if (!res.ok) throw new Error("Failed to post name!");
                else {
                    buttonText = "Submit";
                }
            } catch (error) {
                console.error(error);
            }
            console.log("posted")
        } else {
            console.log("not friend")
            buttonText = "Try again";
        }
    }

    $: isFriend = friends.has(username) || username === "";
    let buttonText = "Submit";

    onMount(getFriends);
</script>

<Navbar />

<div class="register">
    <h2 class="input-title"> Register for Leaderboard:</h2>
    <form class="input-row">
            <input
                id="username"
                type="text"
                bind:value={username}
                style="background: {isFriend ? '' : '#fb4934'};"
                placeholder="Kilter Username"
            />
            <button class="register-submit"
                    type="reset"
                    on:click={postName}>
                { buttonText }
            </button>
    </form>
    <i class="friend_disc">If you want to join the board, you have to be a friend of somebody already on it.</i>
</div>
