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

<div class="comment">
    <i class="points_info">Points will be awarded according to the difficulty difference to your level. <br>
        For a difference of zero, 400 points will be added to your score. In general scoring follows an exponential curve.</i>
</div>
