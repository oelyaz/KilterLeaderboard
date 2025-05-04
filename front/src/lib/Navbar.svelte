<script lang="ts">
    import {goto} from "$app/navigation";

    function navigate_leaderboard() {
        goto("/")
    }

    function navigate_register() {
        goto("/register")
    }

    let logoSrc = '/logo.png'; // Path to the logo in the static folder
</script>

<nav class="navbar">
    <div class="logo">
        <img src="{logoSrc}" alt="logo">
    </div>
    <h1 class="heading">Kilter Leaderboard</h1>
    <div class="buttons">
        <button on:click={navigate_leaderboard}>
            <img src="node_modules/lucide-static/icons/medal.svg" alt="Leaderboard">
        </button>
        <button on:click={navigate_register}>
            <img src="node_modules/lucide-static/icons/circle-plus.svg" alt="Register">
        </button>
    </div>
</nav>
