<h3>Account Information</h3>

<table>
	<tr>
		<td>First name:</td>
		<td><input type="text" name="firstName" placeholder="${sessionScope.account.firstName}"/></td>
	</tr>
	<tr>
		<td>Last name:</td>
		<td><input type="text" name="lastName" placeholder="${sessionScope.account.lastName}" /></td>
	</tr>
	<tr>
		<td>Email:</td>
		<td><input type="text" name="email" placeholder="${sessionScope.account.email}" /></td>
	</tr>
	<tr>
		<td>Phone:</td>
		<td><input type="text" name="phone" placeholder="${sessionScope.account.phone}" /></td>
	</tr>
	<tr>
		<td>Address 1:</td>
		<td><input type="text" name="address1" placeholder="${sessionScope.account.address1}" /></td>
	</tr>
	<tr>
		<td>Address 2:</td>
		<td><input type="text" name="address2" size="40" placeholder="${sessionScope.account.address2}" /></td>
	</tr>
	<tr>
		<td>City:</td>
		<td><input type="text" name="city" placeholder="${sessionScope.account.city}" /></td>
	</tr>
	<tr>
		<td>State:</td>
		<td><input type="text" name="state" size="4"placeholder="${sessionScope.account.state}" /></td>
	</tr>
	<tr>
		<td>Zip:</td>
		<td><input type="text" name="zip" size="10" placeholder="${sessionScope.account.zip}" /></td>
	</tr>
	<tr>
		<td>Country:</td>
		<td><input type="text" name="country" size="15" placeholder="${sessionScope.account.country}" /></td>
	</tr>
</table>

<h3>Profile Information</h3>

<table>
	<tr>
		<td>Language Preference:</td>
		<td>
			<select name="languagePreference" id="languagePreference">
				<c:forEach var="languages" items="${sessionScope.languages}">
					<option>${languages}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td>Favourite Category:</td>
        <td>
            <select name="favouriteCategoryId" id="favouriteCategoryId">
				<c:forEach var="categories" items="${sessionScope.categories}">
					<option>${categories}</option>
				</c:forEach>
            </select>
        </td>
    </tr>
	</tr>
	<tr>
		<td>Enable MyList</td>
		<td><input type="checkbox" name="listOption" value="1" /></td>
	</tr>
	<tr>
		<td>Enable MyBanner</td>
		<td><input type="checkbox" name="bannerOption" value="1" /></td>
	</tr>

</table>
