<script lang="ts">
    import Navbar from "$lib/Navbar.svelte";
    import WinnerBoard from "$lib/WinnerBoard.svelte";
    import "$lib/global.css"
    import "./page.css"

    export const prerender = true;
</script>

<svelte:head>
    <title>Kilter-Leaderboard</title>
</svelte:head>

<Navbar />

<div class = "winner">
    <h2 class = "input-title" > Last season's winner </h2>
    <WinnerBoard />
</div>