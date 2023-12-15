create function public.get_balance_account(account_id uuid) returns numeric
    language plpgsql
as
$$
DECLARE
    account_balance DECIMAL;
BEGIN
    SELECT balance INTO account_balance
    FROM accounts
    WHERE id = account_id;

    RETURN account_balance;
END;
$$;

alter function public.get_balance_account(uuid) owner to postgres;

