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
        leaderboard = [
            { "id": 1, "kilterId": "134", "username": "blub", "name": "Test Player 1", "score": 999, "grade": 12 },
            { "id": 2, "kilterId": "134", "username": "blub", "name": "Test Player 2", "score": 20, "grade": 2 },
            { "id": 3, "kilterId": "134", "username": "blub", "name": "Test Player 3", "score": 3000, "grade": 22 }
        ];
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
                <td>{player.name}</td>
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