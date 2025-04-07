var context = $evaluation.getContext();
var user = context.getIdentity().getSubject();
var attributes = user.getAttributes();

var isActive = attributes.get('isActive') != null && attributes.get('isActive').contains('true');

if (isActive) {
    $evaluation.grant();
} else {
    $evaluation.deny();
}
