<script lang="ts">
    import Leaderboard from "$lib/Leaderboard.svelte";
    import Navbar from "$lib/Navbar.svelte"
    import "$lib/global.css"
    import "./page.css"

    export const prerender = true;
</script>

<svelte:head>
    <title>Kilter-Leaderboard</title>
</svelte:head>

<Navbar />
<Leaderboard />
