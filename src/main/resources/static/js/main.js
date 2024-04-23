const SERVER_URL = 'http://localhost:8080/api/v1/';


document.getElementById('form-joke').addEventListener('submit', getWoW);
document.getElementById('form-joke2').addEventListener('submit', getLoL);
document.getElementById('form-joke3').addEventListener('submit', getSummoner);


async function getWoW(event) {
  // Prevent the form from reloading the page.
  event.preventDefault();

  const URL = `${SERVER_URL}wow?about= + ${document.getElementById('about').value}`
  const spinner = document.getElementById('spinner1');
  const result = document.getElementById('result');
  result.style.color = "black";

  try {
    spinner.style.display = "block";
    const response = await fetch(URL).then(handleHttpErrors)
    document.getElementById('result').innerText = response.answer;
  } catch (e) {
    result.style.color = "red";
    result.innerText = e.message;
  }
  finally {
    spinner.style.display = "none";
  }
}

async function getLoL(event) {
  // Prevent the form from reloading the page.
  event.preventDefault();

  const URL = `${SERVER_URL}lol?about= + ${document.getElementById('about2').value}`
  const spinner = document.getElementById('spinner2');
  const result = document.getElementById('result2');
  result.style.color = "black";

  try {
    spinner.style.display = "block";
    const response = await fetch(URL).then(handleHttpErrors)
    document.getElementById('result2').innerText = response.answer;
  } catch (e) {
    result.style.color = "red";
    result.innerText = e.message;
  }
  finally {
    spinner.style.display = "none";
  }
}


async function handleHttpErrors(res) {
  if (!res.ok) {
    const errorResponse = await res.json();
    const msg = errorResponse.message ? errorResponse.message : "No error details provided"
    throw new Error(msg)
  }
  return res.json()
}


async function getSummoner(event) {
  // Prevent the form from reloading the page.
  event.preventDefault();

  const URL = `${SERVER_URL}summoner?name=${document.getElementById('about3').value}`;
  const spinner = document.getElementById('spinner3');
  const result = document.getElementById('result3');
  result.style.color = "black";

  try {
    spinner.style.display = "block";
    const response = await fetch(URL).then(handleHttpErrors);
    const summonerInfo = response;

    const summonerInfoArray = response;

    if (summonerInfoArray.length > 0) {
      const summonerInfo = summonerInfoArray[0]; // Take the first element as the summoner's information

      const htmlString = `
      <p>Name: ${document.getElementById('about3').value}</p>
      <p>Rank: ${summonerInfo.tier} ${summonerInfo.rank}</p>
      <p>Wins: ${summonerInfo.wins}</p>
      <p>Losses: ${summonerInfo.losses}</p>
    `;
      result.innerHTML = htmlString;
    } else {
      result.innerText = "Summoner not found";
    }
  }catch (e) {
    result.style.color = "red";
    result.innerText = e.message;
  }
  finally {
    spinner.style.display = "none";
  }
}


async function handleHttpErrors(res) {
  if (!res.ok) {
    const errorResponse = await res.json();
    const msg = errorResponse.message ? errorResponse.message : "No error details provided"
    throw new Error(msg)
  }
  return res.json()
}