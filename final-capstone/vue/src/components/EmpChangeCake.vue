<template>
  <div class="change-cake-display">
    <p>Edit Standard Cakes:</p>

    <table id="standard-cakes">
      <thead>
        <tr>
          <th>&nbsp;</th>
          <th>Cake Name</th>
          <th>Description</th>
          <th>Status</th>
          <th>Action</th>
        </tr>
      </thead>
      
      <tbody>
        <tr v-for="cake in $store.state.availableCakeConfigsBE" v-bind:key="cake.cakeConfigID"
            v-bind:class="{ unavailable: !cake.isAvailable}">
          <td>
            <!-- v-bind:id="user.id" v-bind:value="user.id" v-model="selectedUserIDs"  -->
            <input type="checkbox" />
          </td>
          <!-- {{ cake.name }} -->
          <td>{{cake.cakeConfigName}}</td>
          <td>{{cake.cakeConfigDescription}}</td>
          <td>{{cake.isAvailable}}</td>
          <td>
            <!-- v-on:click="flipStatus(cake.id)" >
              {{ cakeConfig.status === "Available" ? "Unavailable" : "Available" }} -->
            <button class="btnAvailableUnavailable"> Flip status</button>
          </td> 
        </tr>
      </tbody>
    </table>

  </div>
</template>

<script>
export default {

}
</script>

<style>
.change-cake-display {
  margin-top: 30px;
  border:2px solid black;
  border-radius: 10px;
}
</style>