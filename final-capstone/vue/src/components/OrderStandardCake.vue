<template>
    <div>
        <div class="cakedisplay">
            </div>
  <form>
      <label for="standard cake selection">Select your standard cake:</label>
      <select name="standard cake"></select><br>
      <label for="optional message">Put what message you want on your cake! (Optional: $1.50 extra)</label>
      <input name="message" type="text"><br>
      <label for="pickup time">When do you want to pick up your cake?</label>
      <input name="pickup time" type="datetime-local">

  </form>
  </div>
</template>

<script>
export default {
    name: "standard order cake",


}
</script>

<style>

</style>