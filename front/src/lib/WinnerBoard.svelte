<script lang="ts">

    import {onMount} from "svelte";

    type Winner = {
        id: number;
        season_year: number;
        season_semester: number;
        username: string;
        score: number;
    };

    let winnerBoard : Winner [] = [];


    async function fetchWinnerBoard() {
        try {
            const result = await fetch ("http://localhost:8080/winner/");  //("https://api.kilterleaderboard.de/winner/");

            if(!result) {
                console.error("Failed to fetch WinnerBoard");
            }
            winnerBoard = await result.json();
        }
        catch (error) {
            console.error(error);
        }
        winnerBoard.sort((a:Winner, b: Winner) => {
            if (a.season_year == b.season_year) {
                return b.season_semester - a.season_semester;
            }
            return b.season_year - a.season_year;
        })



    }
    onMount(fetchWinnerBoard)
</script>

<div>
    <table>
        <thead>
            <tr>
                <th> Season </th>
                <th> Name </th>
                <th> Score </th>
            </tr>
        </thead>
        <tbody>
        {#each winnerBoard as winner}
            <tr>
                <td> {winner.season_year} {#if winner.season_semester === 1} I{:else} II{/if}</td>
                <td> {winner.username}</td>
                <td> {winner.score}</td>
            </tr>
            {/each}
        </tbody>


    </table>


</div>
<style>
    h4 {
        align-content: center;
        text-align: center;
        margin: 13em auto;
    }

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