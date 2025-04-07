<script lang="ts">
    import Navbar from "$lib/Navbar.svelte"
    import "$lib/global.css"
    import "./page.css"

    let username: string="";
</script>

<Navbar />

<div class="register">
        <label class="input-row">Enter username to register:
            <input
                id="username"
                type="text"
                bind:value={username}
                placeholder=""
            />
        </label>
    <i class="friend_disc">If you want to join the board, you have to be a friend of somebody already on it.</i>
</div>
