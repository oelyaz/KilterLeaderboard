<script lang="ts">
    import { onMount } from "svelte";

    type Climber = {
        id: number;
        kilterId: string;
        username: string;
        name: string;
        score: number;
        grade: number;
    };

    let leaderboard: Climber[] = [];

    async function fetchLeaderboard() {
        try {
            const res = await fetch("http://localhost:8080/users/");
            if (!res.ok) throw new Error("Failed to fetch Leaderboard!");
            leaderboard = await res.json();
        } catch (error) {
            console.error(error);
        }
        console.log(leaderboard);
        leaderboard.sort((a: Climber, b: Climber) => b.score - a.score);
    }

    onMount(fetchLeaderboard);
</script>

<table>
    <thead>
        <tr>
            <th>Rank</th>
            <th>Name</th>
            <th>Grade</th>
            <th>Score</th>
        </tr>
    </thead>
    <tbody>
        {#each leaderboard as player, index}
            <tr>
                <td>{index+1}</td>
                <td>{player.username}</td>
                <td>{player.grade}</td>
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