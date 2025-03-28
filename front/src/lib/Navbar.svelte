<script lang="ts">
    import {goto} from "$app/navigation";

    function navigate_leaderboard() {
        goto("/")
    }

    function navigate_register() {
        goto("/register")
    }
</script>

<nav>
    <h1>Klieterboard</h1>
    <button on:click={navigate_leaderboard}>Leaderboard</button>
    <button on:click={navigate_register}>Register</button>
</nav>
