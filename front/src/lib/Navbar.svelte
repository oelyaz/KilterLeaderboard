<script lang="ts">
    import {goto} from "$app/navigation";

    function navigate_leaderboard() {
        goto("/")
    }

    function navigate_register() {
        goto("/register")
    }

    function navigate_winner() {
        goto("/winner")
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
            <img src="/medal.svg" alt="Leaderboard">
        </button>
        <button on:click={navigate_winner}>
            <img src="/trophy.svg" alt="Winner">
        </button>
        <button on:click={navigate_register}>
            <img src="/circle-plus.svg" alt="Register">
        </button>
    </div>
</nav>
