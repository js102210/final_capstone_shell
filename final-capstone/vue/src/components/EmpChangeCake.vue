<template>
  <div class="change-cake-display">
    <p>Edit Standard Cakes:</p>

    <table id="standard-cakes">
      <thead>
        <tr>
          <th>&nbsp;</th>
          <th>Cake Name</th>
          <th>Flavor</th>
          <th>Description</th>
          <th>Status</th>
          <th>Action</th>
        </tr>
      </thead>
<!--       
      <tbody>
        <tr>
          <td>
            <select id="statusFilter" v-model="filter.status">
              <option value>Show All</option>
              <option value="Active">Active</option>
              <option value="Disabled">Disabled</option>
            </select>
          </td>
          <td>&nbsp;</td>
        </tr>
        <tr v-for="user in filteredList" v-bind:key="user.id"
          v-bind:class="{ disabled: user.status === 'Disabled' }">
          <td>
            <input type="checkbox" v-bind:id="user.id" v-bind:value="user.id" v-model="selectedUserIDs" />
          </td>
          <td>{{ user.firstName }}</td>
          <td>{{ user.lastName }}</td>
          <td>{{ user.username }}</td>
          <td>{{ user.emailAddress }}</td>
          <td>{{ user.status }}</td>
          <td>
            <button class="btnEnableDisable" v-on:click="flipStatus(user.id)" >
              {{ user.status === "Active" ? "Disable" : "Enable" }}</button>
          </td> 
        </tr>
      </tbody> -->
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