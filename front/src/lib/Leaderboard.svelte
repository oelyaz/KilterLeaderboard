<script lang="ts">
    import { onMount } from "svelte";

    type Climber = {
        name: string;
        score: number;
    };

    let leaderboard: Climber[] = [];

    async function fetchLeaderboard() {
        try {
            const res = await fetch("http://localhost:8080/api/leaderboard");
            if (!res.ok) throw new Error("Failed to fetch Leaderboard!");
            leaderboard = await res.json();
        } catch (error) {
            console.error(error);
            leaderboard = [
                { name: "Test Player 1", score: 999 },
                { name: "Test Player 2", score: 850 },
                { name: "Test Player 3", score: 720 }
            ];
        }
    }

    onMount(fetchLeaderboard);
</script>

<table>
    <thead>
        <tr>
            <th>Rank</th>
            <th>Name</th>
            <th>Score</th>
        </tr>
    </thead>
    <tbody>
        {#each leaderboard as player, index}
            <tr>
                <td>{index+1}</td>
                <td>{player.name}</td>
                <td>{player.score}</td>
            </tr>
        {/each}
    </tbody>
</table>

<style>
    table {
        width: 100%;
        border-collapse: collapse;
    }
    th, td {
        border: 1px solid #ddd;
        padding: 8px;
        text-align: left;
    }
    th {
        background-color: #f4f4f4;
    }
</style>